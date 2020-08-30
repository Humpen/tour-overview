package de.hsbhv.touroverview.backend.graphql;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class QueryManager {

    public static JSONObject getAllTours(){
        GraphQLConnector graphQLConnector = new GraphQLConnector();
        String query = "query {" +
                "  tours {" +
                "    name " +
                "    id " +
                "  } " +
                "}";
        return graphQLConnector.sendRequest(graphQLConnector.createRequest(query));
    }
    //@Nico - Kein Anspruch auf Vollständigkeit
    public static JSONObject getAllToursAllDetails(){
        GraphQLConnector graphQLConnector = new GraphQLConnector();
        String query = "query{" +
                "  tours { " +
                "    name " +
                "    id " +
                "    strecke " +
                "    dauer " +
                "    publishedAt " +
                "    stage " +
                "    updatedAt " +
                "    bewertungen " +
                "    createdAt " +
                "    sehenswuerdigkeiten { " +
                "      createdAt " +
                "      radius " +
                "      name " +
                "      id " +
                "      modelle { " +
                "        name " +
                "        id " +
                "        createdAt " +
                "        positionX " +
                "        positionY " +
                "        positionZ " +
                "        publishedAt " +
                "        rotation " +
                "        skalierungX " +
                "        skalierungY " +
                "        skalierungZ " +
                "        updatedAt " +
                "      } " +
                "      infoText { " +
                "        html " +
                "        raw " +
                "        text " +
                "      } " +
                "      bilder { " +
                "        createdAt " +
                "        fileName " +
                "        url " +
                "        externUrl " +
                "        id " +
                "        locale " +
                "        height " +
                "        width " +
                "        handle " +
                "        modellObjekt { " +
                "          createdAt " +
                "          id " +
                "          name " +
                "          positionX " +
                "          positionY " +
                "          positionZ " +
                "          rotation " +
                "          publishedAt " +
                "          skalierungX " +
                "          skalierungY " +
                "          skalierungZ " +
                "          updatedAt " +
                "        } " +
                "        modellObjektMtl { " +
                "          name " +
                "          positionX " +
                "          positionY " +
                "          positionZ " +
                "          rotation " +
                "          createdAt " +
                "          id " +
                "          skalierungX " +
                "          skalierungY " +
                "          skalierungZ " +
                "        } " +
                "      } " +
                "      position { " +
                "        latitude " +
                "        longitude " +
                "      } " +
                "    } " +
                "    startpunkt { " +
                "      latitude " +
                "      longitude " +
                "      distance(from: {latitude: 1.5, longitude: 1.5}) " +
                "    } " +
                "  } " +
                "}";
        query = "query MyQuery {\n" +
                "  tours {\n" +
                "    name\n" +
                "    id\n" +
                "    strecke\n" +
                "    dauer\n" +
                "    publishedAt\n" +
                "    stage\n" +
                "    updatedAt\n" +
                "    bewertungen\n" +
                "    createdAt\n" +
                "    sehenswuerdigkeiten {\n" +
                "      createdAt\n" +
                "      radius\n" +
                "      name\n" +
                "      id\n" +
                "      modelle {\n" +
                "        name\n" +
                "        id\n" +
                "        createdAt\n" +
                "        positionX\n" +
                "        positionY\n" +
                "        positionZ\n" +
                "        publishedAt\n" +
                "        rotation\n" +
                "        skalierungX\n" +
                "        skalierungY\n" +
                "        skalierungZ\n" +
                "        updatedAt\n" +
                "      }\n" +
                "      infoText {\n" +
                "        html\n" +
                "        raw\n" +
                "        text\n" +
                "      }\n" +
                "      bilder {\n" +
                "        createdAt\n" +
                "        fileName\n" +
                "        url\n" +
                "        externUrl\n" +
                "        id\n" +
                "        locale\n" +
                "        height\n" +
                "        width\n" +
                "        handle\n" +
                "        modellObjekt {\n" +
                "          createdAt\n" +
                "          id\n" +
                "          name\n" +
                "          positionX\n" +
                "          positionY\n" +
                "          positionZ\n" +
                "          rotation\n" +
                "          publishedAt\n" +
                "          skalierungX\n" +
                "          skalierungY\n" +
                "          skalierungZ\n" +
                "          updatedAt\n" +
                "        }\n" +
                "        modellObjektMtl {\n" +
                "          name\n" +
                "          positionX\n" +
                "          positionY\n" +
                "          positionZ\n" +
                "          rotation\n" +
                "          createdAt\n" +
                "          id\n" +
                "          skalierungX\n" +
                "          skalierungY\n" +
                "          skalierungZ\n" +
                "        }\n" +
                "      }\n" +
                "      position {\n" +
                "        latitude\n" +
                "        longitude\n" +
                "      }\n" +
                "    }\n" +
                "    startpunkt {\n" +
                "      latitude\n" +
                "      longitude\n" +
                "      distance(from: {latitude: 1.5, longitude: 1.5})\n" +
                "    }\n" +
                "  }\n" +
                "}";
        return graphQLConnector.sendRequest(graphQLConnector.createRequest(query, null, ""));
    }
    //Funktioniert nicht - Die Scheiße...
    public static JSONObject getTourByName(){
        GraphQLConnector graphQLConnector = new GraphQLConnector();
        String query = "query MyQuery{\n" +
                "  tour (where: {\"name\": \"Tour 1\"}) {\n" +
                "    name\n" +
                "    id\n" +
                "    dauer\n" +
                "    strecke\n" +
                "    beschreibung{\n" +
                "      html\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Map<String, String> variables = new HashMap<>();
//        variables.put("where:", "name: " + name +"})");
//        return graphQLConnector.sendRequest(graphQLConnector.createRequest(query, variables));
        return graphQLConnector.sendRequest(graphQLConnector.createRequest(query));

    }
    //Löppt der Lachs
    public static JSONObject getTourByName(String name){
        GraphQLConnector graphQLConnector = new GraphQLConnector();
        String query = "query MyQuery{\n" +
                "  tour (where: {\"name\": \"Tour 1\"}) {\n" +
                "    name\n" +
                "    id\n" +
                "    dauer\n" +
                "    strecke\n" +
                "    beschreibung{\n" +
                "      html\n" +
                "    }\n" +
                "  }\n" +
                "}";
        query = "query($names:String){\n" +
                "  tour(where: {name: $names}){\n" +
                "    name\n" +
                "    id\n" +
                "    dauer\n" +
                "    strecke\n" +
                "    beschreibung{\n" +
                "      html\n" +
                "    }\n" +
                "  }\n" +
                "}\n" +
                "\n";
        Map<String, String> variables = new HashMap<>();
        variables.put("names", "Tour 1");
//        return graphQLConnector.sendRequest(graphQLConnector.createRequest(query, variables));
        return graphQLConnector.sendRequest(graphQLConnector.createRequest(query, variables, ""));
    }




}
