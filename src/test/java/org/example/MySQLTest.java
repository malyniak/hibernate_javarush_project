package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.project.HibernateUtil;
import org.project.dao.CityDAO;
import java.util.*;

public class MySQLTest {
    private SessionFactory sessionFactory= HibernateUtil.getMySqlSessionFactory();
    private CityDAO cityDAO=CityDAO.getInstance();
    private ObjectMapper objectMapper=new ObjectMapper();
   private List<Integer> ids = List.of(1, 122, 2, 547, 1021, 625, 878);
    @Test void testMySQLData() {
        try(var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            for(Integer id : ids) {
                var optionalCity = cityDAO.findById(id);
                if(optionalCity.isPresent()) {
                    var country = optionalCity.get().getCountry();
                    var countryLanguages = country.getCountryLanguage();
                }
            }
            transaction.commit();
        }
    }
}
