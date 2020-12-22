package cl.hector.arqutipo_web.repositories;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.hector.arqutipo_web.mappers.SpringBootWebAppMapper;
import cl.hector.arqutipo_web.models.SpringBootWebAppTestPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class SpringBootWebAppRepo {

	@Autowired 
	private SpringBootWebAppMapper mapper;

	public SpringBootWebAppTestPojo obtieneTest(Long idTest) {
		return mapper.getTest(idTest);
	}

	public void insertTest(SpringBootWebAppTestPojo testPojo) {
		if (testPojo == null) {
			throw new MineducException("No se puede ingresar objeto nulo.");
		}
		try {
			mapper.insertTest(testPojo);
		} catch (DataAccessException e) {
			log.error("Error de base de datos al ingrear una entidad", e);
			throw new MineducException("Error de base de datos al ingrear una entidad", e);
		}
	}
}