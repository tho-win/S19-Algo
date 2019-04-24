//package roadTrip;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestRunner {

    @Test
    public void exampleTest() {
        List<City> cities = new ArrayList<City>();
        List<Float> coords1 = new ArrayList<Float>();
        coords1.add(2.8f);
        coords1.add(0.9f);

        List<Float> coords2 = new ArrayList<Float>();
        coords2.add(2.3f);
        coords2.add(2.6f);

        List<Float> coords3 = new ArrayList<Float>();
        coords3.add(0.5f);
        coords3.add(1.8f);

        List<Float> coords4 = new ArrayList<Float>();
        coords4.add(1.9f);
        coords4.add(1.2f);

        List<Float> coords5 = new ArrayList<Float>();
        coords5.add(2.3f);
        coords5.add(1.8f);


        List<Float> coords6 = new ArrayList<Float>();
        coords6.add(1.3f);
        coords6.add(1.2f);


        List<Float> coords7 = new ArrayList<Float>();
        coords7.add(1.2f);
        coords7.add(0.3f);


        cities.add(new City("1", coords1));
        cities.add(new City("2", coords2));
        cities.add(new City("3", coords3));
        cities.add(new City("4", coords4));
        cities.add(new City("5", coords5));
        cities.add(new City("6", coords6));
        cities.add(new City("7", coords7));

        List<Road> roads = new ArrayList<Road>();
        roads.add(new Road("1", cities.get(0), cities.get(2)));
        roads.add(new Road("2", cities.get(0), cities.get(1)));
        roads.add(new Road("3", cities.get(1), cities.get(4)));
        roads.add(new Road("4", cities.get(2), cities.get(3)));
        roads.add(new Road("5", cities.get(3), cities.get(4)));
        roads.add(new Road("6", cities.get(4), cities.get(5)));

        Graph g = new Graph(cities, roads);

        int[][] tolls = new int[3][3];
        tolls[0][0] = 3;
        tolls[0][1] = 5;
        tolls[0][2] = 1;
        tolls[1][0] = 5;
        tolls[1][1] = 4;
        tolls[1][2] = 2;
        tolls[2][0] = 3;
        tolls[2][1] = 1;
        tolls[2][2] = 1;


        // TESTING MY OWN SOLUTION


        // EXPECTED, ACTUAL
        RoadTrip rt = new RoadTrip(g, tolls);
        rt.computeCostsFromSource(cities.get(0));
        List<City> expectedPath = new ArrayList<City>();
        expectedPath.add(cities.get(0));
        expectedPath.add(cities.get(1));
        expectedPath.add(cities.get(4));
        expectedPath.add(cities.get(5));
        Assert.assertEquals(expectedPath, rt.getPath(cities.get(5)));

    }

    // WILL add tests for RoadTrip against RoadTripSolution that will fail
}