package ru.rambler.skanerxxl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Route {

	// private String cityOfDeparture;
	Map<String, Point> citiesMap = new HashMap<>();
	private HashSet<String> listOfFixedPoints = new HashSet<>();

	public void findRoute(int startingPoint, int finishPoint, String[] cities, int[][] routeAndPriceMap) {
		// this.cityOfDeparture = cities[startingPoint];
		Map<String, Integer> listOfPoints = new HashMap<>();

		// for citiesMap
		addCity(startingPoint, cities);

		// Search for nearby cities, until the finish point is fixed
		for (; !this.citiesMap.get(cities[finishPoint]).isPointStatus();) {
			// System.out.println("for '" + cities[startingPoint] + "' -> to '"
			// + cities[finishPoint] + "'");

			// go on routes city of departure
			for (int neighborIndex = 0; neighborIndex < routeAndPriceMap[startingPoint].length; neighborIndex++) {
				// If there is a transport connection with a neighbor
				if (routeAndPriceMap[startingPoint][neighborIndex] > 0) {
					// System.out.println("city of departure '" +
					// cities[startingPoint] + "' -> to neighbor '"
					// + cities[neighborIndex] + "'");

					Point neighbor = this.citiesMap.get(cities[neighborIndex]);
					// If PointStatus = true then the value of the price can not
					// be changed
					if (neighbor.isPointStatus()) {
						continue;
					}

					// If the route is new
					if (neighbor.getPrice() == -1) {
						Point pointDeparture = this.citiesMap.get(cities[startingPoint]);
						int price = pointDeparture.getPrice();
						price = (price <= 0) ? routeAndPriceMap[startingPoint][neighborIndex]
								: price + routeAndPriceMap[startingPoint][neighborIndex];
						neighbor.setPrice(price);
						neighbor.setPointOfDeparture(cities[startingPoint]);
						// if (!listOfNoFixedPoints.containsKey(cities[i])) {
						listOfPoints.put(cities[neighborIndex], neighborIndex);
						// }
						continue;
					}

					// If the new route is more economical
					if (this.citiesMap.get(cities[startingPoint]).getPrice()
							+ routeAndPriceMap[startingPoint][neighborIndex] < this.citiesMap.get(cities[neighborIndex])
									.getPrice()) {
						// Point oldPoint =
						// this.citiesMap.get(cities[neighborIndex]);
						neighbor.setPrice(this.citiesMap.get(cities[startingPoint]).getPrice()
								+ routeAndPriceMap[startingPoint][neighborIndex]);
						neighbor.setPointOfDeparture(cities[startingPoint]);
						// if (!listOfPoints.containsKey(cities[neighborIndex]))
						listOfPoints.put(cities[neighborIndex], neighborIndex);
						continue;
					}
					// If none of the conditions are satisfied
					if (!listOfPoints.containsKey(cities[neighborIndex]))
						listOfPoints.put(cities[neighborIndex], neighborIndex);
				}
			}
			bestRoute(listOfPoints);

			Point point = this.citiesMap.get(this.bestCity);
			point.setPointStatus(true);
			startingPoint = this.indexBestCity;
			this.listOfFixedPoints.add(cities[startingPoint]);
		}
		bestRoute(this.bestCity);
	}

	List<String> finalRoute = new ArrayList<>();

	private void bestRoute(String finish) {
		Point point = this.citiesMap.get(finish);
		this.finalRoute.add("\nñity of arrival - " + finish + "\nroute price = " + point.getPrice());

		recursionBestRoute(finish);

		Collections.reverse(this.finalRoute);
		for (String s : this.finalRoute)
			System.out.print(s);
	}

	private void recursionBestRoute(String point) {
		Point p = this.citiesMap.get(point);
		if (p.getPointOfDeparture().equals(p.getMyPointName())) {
			this.finalRoute.add("\ncity of departure " + p.getMyPointName() + "\nfull path:");
			return;
		} else
			this.finalRoute.add("\n" + p.getPointOfDeparture() + " -> " + p.getMyPointName());

		recursionBestRoute(p.getPointOfDeparture());
	}

	private void addCity(int startingPoint, String[] cities) {
		for (int i = 0; i < cities.length; i++) {
			if (cities[i].equals(cities[startingPoint])) {
				this.citiesMap.put(cities[i], new Point(cities[i], 0, cities[startingPoint], true));
				this.listOfFixedPoints.add(cities[startingPoint]);
				continue;
			}
			this.citiesMap.put(cities[i], new Point(cities[i]));
		}
	}

	private int bestPrice;
	private String bestCity;
	private int indexBestCity;

	/* Looking for the most economical route among non-fixed points */
	private void bestRoute(Map<String, Integer> listOfCities) {
		this.bestPrice = 0;
		Set<String> set = listOfCities.keySet();
		for (String city : set) {
			Point bus = citiesMap.get(city);

			if (!this.listOfFixedPoints.contains(city) && (this.bestPrice == 0 || bus.getPrice() < this.bestPrice)) {
				this.bestPrice = bus.getPrice();
				this.bestCity = city;
				this.indexBestCity = listOfCities.get(city);
			}
		}
	}
}
