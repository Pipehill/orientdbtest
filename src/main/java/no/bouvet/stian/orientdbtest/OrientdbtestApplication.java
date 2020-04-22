package no.bouvet.stian.orientdbtest;

import com.google.gson.Gson;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.sql.executor.OResult;
import com.orientechnologies.orient.core.sql.executor.OResultSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class OrientdbtestApplication {

    @RequestMapping("/")
    public String home() {

        System.out.println("Returning data...");

        List<String> toReturn = new ArrayList();

        OrientDB orientDB = new OrientDB("remote:db", OrientDBConfig.defaultConfig());
        ODatabaseDocument db = orientDB.open("mydb","root", "root");

        String statement = "SELECT FROM Bruker";
        OResultSet rs = db.query(statement);
        while(rs.hasNext()){
            OResult row = rs.next();
            toReturn.add(row.getProperty("Navn"));
        }
        rs.close();

        return new Gson().toJson(toReturn);
    }

    public static void main(String[] args) {
        SpringApplication.run(OrientdbtestApplication.class, args);

        System.out.println("Started...");
    }
}
