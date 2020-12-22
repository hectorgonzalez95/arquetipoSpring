package cl.hector.arqutipo_web.services;

import cl.hector.arqutipo_web.models.SpringBootWebAppTestPojo;
import cl.hector.arqutipo_web.repositories.SpringBootWebAppRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpringBootWebAppService {

	@Autowired
	private SpringBootWebAppRepo repositorio;
	
	public SpringBootWebAppTestPojo obtieneTestPorId(Long idTest) {
		return repositorio.obtieneTest(idTest);
	}

}