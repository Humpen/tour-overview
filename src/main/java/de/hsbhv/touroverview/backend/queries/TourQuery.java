package de.hsbhv.touroverview.backend.queries;

import de.hsbhv.touroverview.backend.entities.Tour;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLResponseEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;

import java.net.MalformedURLException;

public class TourQuery implements QueryHandler{
//    private Arguments arguments = new ArrayList<Argument>();

    public TourQuery(){
//        arguments.add(new Argument<String>("name", "Tour 1"));
    }

    public Tour getTourByName(String Name) throws MalformedURLException {
        setAuthToken(standardToken);
        GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                .url("https://api-eu-central-1.graphcms.com/v2/ckbtfd1r500dl01z51rpf93bm/master")
                .headers(headers)
//                .arguments(Argume)
                .request(Tour.class)
                .build();
        GraphQLTemplate graphQLTemplate = new GraphQLTemplate();
        GraphQLResponseEntity<Tour> tour = graphQLTemplate.query(requestEntity, Tour.class);

        return null;
    }
}
