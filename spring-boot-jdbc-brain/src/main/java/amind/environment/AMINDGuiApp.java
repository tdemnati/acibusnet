package amind.environment;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@ComponentScan
@RestController("/")
public class AMINDGuiApp extends WebMvcConfigurerAdapter{

    public static final String NEO4J_URL = System.getProperty("NEO4J_URL","jdbc:neo4j://localhost:7474");
    public static String OUTPUT = new String();
    		
    public static final RowMapper<Input> INPUT_ROW_MAPPER = new RowMapper<Input>() {
        public Input mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Input(rs.getString("input"));
        }
    };
    
    public static final RowMapper<Output> OUTPUT_ROW_MAPPER = new RowMapper<Output>() {
        public Output mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Output(rs.getString("output"));
        }
    };

    // Template query: get movie
    @Autowired
    JdbcTemplate template;

    //Definition of the class Input and Output
    public static class Input {
        public String input;

        public Input(String input) {
            this.input = input;
        }
    }
    
    public static class Output {
        public String output;

        public Output(String output) {
            this.output = output;
        }
    }
    
    
    @RequestMapping("/search")
    public List<Input> search(@RequestParam("q") String query) {
        if (query==null || query.trim().isEmpty()) return Collections.emptyList();
        OUTPUT=query;
        String queryParam = "(?i).*" + "AMIND" + ".*";
        System.out.print("FIRE INPUT/n");
        return template.query("RETURN " + "'" + query + "'"+ " as input", INPUT_ROW_MAPPER, queryParam);
    }
    
    
    @RequestMapping("/output")
    public List<Output> output() {
        System.out.print("FIRE OUTPUT/n");
        return template.query("RETURN '" + OUTPUT+ "' as output", OUTPUT_ROW_MAPPER, "");
    }

    public static final String GRAPH_QUERY = "MATCH (m)<-[r]-(a) "+
    "RETURN r.roles as rel, "+
    "m.name as node2, "+
    "a.name as node1" +
            " LIMIT {1}";
   
    @RequestMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        Iterator<Map<String, Object>> result = template.queryForList(
                GRAPH_QUERY, limit == null ? 100 : limit).iterator();
        return toD3Format(result);
    }
    

    //Links and Nodes creation. 
    private Map<String, Object> toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> rels= new ArrayList<Map<String,Object>>();
              
        while (result.hasNext()) {

        	Map<String, Object> row = result.next();
  	
            //Create target i for Node2
            Map<String, Object> node2 = map("input", row.get("node2"),"label","nodeStyle");
            System.out.print(" | NODE2:" + !nodes.contains(node2));
            if(!nodes.contains(node2))
            nodes.add(node2);
            int target = nodes.indexOf(node2);
            
            //Create source i for Node1
            Map<String, Object> node1 = map("input", row.get("node1"),"label","nodeStyle");
            System.out.print(" | NODE1:" + !nodes.contains(node1));
            if(!nodes.contains(node1))
            nodes.add(node1);
            int source = nodes.indexOf(node1); 
            
            //Adding relationship to "rels"
            Map<String, Object> relationship = map("source",source,"target",target);
            relationship.put("relationship", row.get("rel"));
            rels.add(relationship);
        }
        //Make relation between Nodes and Links
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String,Object>(2);
        result.put(key1,value1);
        result.put(key2,value2);
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.setErr(new PrintStream(System.out) {
            @Override
            public void write(int b) {
                super.write(b);
            }

            @Override
            public void write(byte[] buf, int off, int len) {
                super.write(buf, off, len);
            }
        });
        new SpringApplicationBuilder(AMINDGuiApp.class).run(args);
    }

    //Database connection with authentication
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(NEO4J_URL,"neo4j","tietie666");
    }
}
