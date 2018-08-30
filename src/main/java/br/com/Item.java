package br.com;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class Item {

    @Id
    public String id;
    public String name;
    public String qty;
    public String price;

    public Item() {}

    public Item(String name, String qty, String price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%s, name='%s', qty='%s', price='%s']", id, name, qty, price);
    }
}
