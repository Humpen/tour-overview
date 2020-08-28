package de.hsbhv.touroverview.backend.entities;

import io.aexp.nodes.graphql.annotations.GraphQLProperty;

@GraphQLProperty(name="tour")
public class Tour {
    String name;
    int radius;
    //List<String> bilder;
}
