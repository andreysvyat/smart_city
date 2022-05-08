package ru.krd.smc.service.client.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.krd.smc.service.client.AddressRequestor;

@Log4j2
@Service
public class MockAddressRequestor implements AddressRequestor {
	@Override
	public String getAddress(String location) {
		log.warn("Mock AddressRequestor implementation");
		return "Address for coordinates " + location;
	}
}
