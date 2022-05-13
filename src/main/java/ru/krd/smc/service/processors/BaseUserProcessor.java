package ru.krd.smc.service.processors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.krd.smc.dba.CityCaseRepo;
import ru.krd.smc.dba.ContractorRepo;
import ru.krd.smc.dba.UserRepo;
import ru.krd.smc.model.entity.CityCase;
import ru.krd.smc.model.entity.ContractorInfo;
import ru.krd.smc.model.entity.User;
import ru.krd.smc.model.resp.UserInfo;
import ru.krd.smc.model.resp.UserInfoShort;
import ru.krd.smc.model.rq.NewUser;
import ru.krd.smc.service.UserProcessor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.krd.smc.model.enums.UserType.CONTRACTOR;
import static ru.krd.smc.model.enums.UserType.OPERATOR;

@Service
@Log4j2
@RequiredArgsConstructor
public class BaseUserProcessor implements UserProcessor {

	private final UserRepo userRepo;
	private final ContractorRepo contractorRepo;
	private final CityCaseRepo cityCaseRepo;

	@Override
	public User findUser(UUID userId) {
		return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found " + userId));
	}

	@Override
	public List<User> getOperators() {
		return userRepo.findAllByType(OPERATOR);
	}

	@Override
	public UserInfo getUserInfo(UUID userId) {
		return userRepo.findById(userId)
				.map(this::toUserInfo)
				.orElseThrow(() -> new RuntimeException("User not found " + userId));
	}

	@Override
	public List<UserInfoShort> getAll() {
		return StreamSupport.stream(userRepo.findAll().spliterator(), false)
				.map(this::toUserInfoShort)
				.collect(Collectors.toList());
	}

	@Override
	public UserInfo addNewUser(NewUser user) {
		User eUser = userRepo.save(
				User.builder()
						.firstName(user.getFirstName())
						.lastName(user.getLastName())
						.login(user.getLogin())
						.middleName(user.getMiddleName())
						.type(user.getType())
						.email(user.getEmail())
						.build());
		return toUserInfo(eUser);
	}

	private UserInfo toUserInfo(User it){
		return UserInfo.builder()
				.fio(getFio(it))
				.id(it.getId().toString())
				.type(it.getType())
				.orgName(it.getType() == CONTRACTOR ? contractorRepo.findByUser(it)
						.map(ContractorInfo::getFullName)
						.orElse(null) : null)
				.email(it.getEmail())
				.cases(cityCaseRepo.findAllByAuthor(it)
						       .stream()
						       .map(CityCase::getId)
						       .map(UUID::toString)
						       .collect(Collectors.toList()))
				.build();
	}

	private UserInfoShort toUserInfoShort(User it) {
		return UserInfoShort.builder()
				.fio(getFio(it))
				.id(it.getId().toString())
				.type(it.getType())
				.orgName(it.getType() == CONTRACTOR ? contractorRepo.findByUser(it)
						.map(ContractorInfo::getFullName)
						.orElse(null) : null)
				.build();
	}

	private String getFio(User it){
		return String.join(" ", it.getMiddleName(), it.getFirstName(), it.getLastName());
	}
}
