package de.hsbhv.touroverview.backend.entities;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;

//@GraphQLProperty(name="tours", arguments = {@GraphQLArgument(name="name", value = "Tour 1")})
@GraphQLProperty(name="tours")
public class Tour {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

/*    public int radius;
    public int getRadius() {
        return radius;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }*/
    //List<String> bilder;
}
