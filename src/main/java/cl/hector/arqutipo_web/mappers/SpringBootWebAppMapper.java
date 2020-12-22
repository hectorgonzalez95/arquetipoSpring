package cl.hector.arqutipo_web.mappers;

import cl.hector.arqutipo_web.models.SpringBootWebAppTestPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SpringBootWebAppMapper {
	
	SpringBootWebAppTestPojo getTest(@Param(value = "idTest") Long idTest);
	void insertTest(@Param(value = "testPojo") SpringBootWebAppTestPojo testPojo);
}