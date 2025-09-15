package com.example.spring08.service;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring08.dto.PwdChangeRequest;
import com.example.spring08.dto.UserDto;
import com.example.spring08.exception.PasswordException;
import com.example.spring08.repository.UserDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserDao dao;
	// 비밀번호를 암호화 하기 위한 객체도 spring bean container 로부터 주입받는다.
	private final PasswordEncoder encoder;
	// 업로드된 이미지를 저장할 위치 얻어내기
	@Value("${file.location}")
	private String fileLocation;
	
	// 사용자를 추가하는 메소드
	@Override
	public void createUser(UserDto dto) {
		// 날 것의 비밀번호를 암호화해서
		String encodedPwd = encoder.encode(dto.getPassword());
		// dto 에 다시 담고
		dto.setPassword(encodedPwd);
		// DB 에 저장하기
		dao.insert(dto);
	}

	@Override
	public UserDto getUser(String userName) {
		
		return dao.getByUserName(userName);
	}

	@Override
	public void updatePassword(PwdChangeRequest pcr) {
		// 로그인 된 userName
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		// DB 에 저장된 암호화 된 비밀번호를 읽어온다.
		UserDto dto = dao.getByUserName(userName);
		String encodedPwd = dto.getPassword();
		// 암호화 된 비밀번호와 입력한 비밀번호를 비교해서 일치하는지 확인한다
		boolean isValid = BCrypt.checkpw(pcr.getPassword(), encodedPwd);
		// 만약 일치하지 않으면 예외 발생 시키기
		if(!isValid) {
			throw new PasswordException("기존 비밀번호가 일치하지 않습니다");
		}
		// 일치하면 새 비밀번호를 암호화 해서 pcr 객체에 담고 DB 에 수정 반영한다.
		dto.setPassword(encoder.encode(pcr.getNewPassword()));
		dao.updatePassword(dto);
		
	}

	@Override
	public Map<String, Object> canUseId(String id) {
		// id 를 이용해서 DB 에 해당 아이디로 가입된 정보가 있는지 읽어와본다 (없으면 null)
		UserDto dto = dao.getByUserName(id);
		// id 가 사용가능한지 여부 (dto 가 null 이면 사용 가능한 아이디 이다)
		boolean canUse = dto == null ? true : false;
		// Map 에 담아서 리턴한다
		return Map.of("canUse", canUse);
	}

	@Override
	public void updateUser(UserDto dto) {
		// 업로드된 이미지가 있는지 읽어와본다
		MultipartFile image = dto.getProfileFile();
		// 만약 업로드된 이미지가 있다면
		if(!image.isEmpty()) {
			// 원본 파일명
			String orgFileName = image.getOriginalFilename();
			// 이미지의 확장자를 유지하기 위해 뒤에 원본 파일명을 추가한다
			String saveFileName = UUID.randomUUID().toString() + orgFileName;
			// 저장할 파일의 전체 경로 구성하기
			String filePath = fileLocation + File.separator + saveFileName;
			try {
				// 업로드 된 파일을 저장할 파일 객체 생성
				File saveFile = new File(filePath);
				image.transferTo(saveFile);
			} catch(Exception e) {
				e.printStackTrace();
			}
			// UserDto 에 저장된 이미지의 이름을 넣어준다.
			dto.setProfileImage(saveFileName);
		}
		// UserDao 객체를 이용해서 수정 반영하기(dto 의 profileImage 는 null 일 수도 있다)
		dao.update(dto);
	}
}
