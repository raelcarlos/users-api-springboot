package br.com;

import com.google.gson.Gson;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ItemRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String findAll() {
        StringBuilder s = new StringBuilder();
        List<Item> itens = repository.findAll();
        for (Item item : itens) {
            s.append(item.toString());
        }
        return s.toString();
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity addItem(@RequestBody String jsonString){
        Document doc = Document.parse(jsonString);
        mongoTemplate.insert(doc, "users");
        // faltaria verificar se realmente inseriu e dar o retorno correto.
        return ResponseEntity.ok(jsonString);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable String id){
        Item result = mongoTemplate.findById(id, Item.class);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(result));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable String id){
        DeleteResult result = mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), Item.class);
        Gson gson = new Gson();
        return ResponseEntity.ok(gson.toJson(result));
    }
}
