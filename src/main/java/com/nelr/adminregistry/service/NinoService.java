package com.nelr.adminregistry.service;

import java.util.List;

import com.nelr.adminregistry.dto.NinoDTO;
import com.nelr.adminregistry.exception.NinoException;

public interface NinoService {

	public String addNino(NinoDTO ninoDTO) throws NinoException, Exception;
	public List<NinoDTO> getNinosByPersona() throws NinoException;
}
