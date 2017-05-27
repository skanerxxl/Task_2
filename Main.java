package ru.rambler.skanerxxl;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		/*
		 * list of cities, the city index corresponds to the index in the route
		 * map
		 */
		String[] cities = { "Kiev", "Irpen", "Fastiv", "Borispol", "Belaya Tserkov", "Vinnitsa", "Berdichev",
				"Zhitomir", "Khmelnitskiy", "Shepetovka", "Novograd-Volynskiy", "Korosten" };

		// String[] cities = { "a", "b", "c", "d", "e", "f" };

		listOfCitiesToConsole(cities);

		Scanner scanner = new Scanner(System.in);
		int startingPoint;
		int finishPoint;

		System.out.println("\nEnter city number - starting point");
		startingPoint = scanner.nextInt() - 1;
		System.out.println("\nEnter city number - finish point");
		finishPoint = scanner.nextInt() - 1;
		scanner.close();

		/* route map with appropriate costs */
		int[][] routeAndPriceMap = {

				{ 0, 15, 10, 30, 35, 0, 0, 53, 0, 0, 0, 60 }, // 1-Kiev
				{ 15, 0, 15, 35, 0, 0, 0, 79, 0, 0, 0, 55 }, // 2-Irpen
				{ 10, 15, 0, 20, 9, 0, 71, 68, 0, 0, 0, 0 }, // 3-Fastiv
				{ 30, 0, 20, 0, 25, 0, 0, 0, 0, 0, 0, 0 }, // 4-Borispol
				{ 35, 0, 9, 25, 0, 92, 83, 0, 0, 0, 0, 0 }, // 5-BelayaTserkov
				{ 0, 0, 0, 0, 92, 0, 74, 0, 87, 102, 0, 0 }, // 6-Vinnitsa
				{ 0, 0, 71, 0, 83, 74, 0, 24, 116, 103, 0, 0 }, // 7-Berdichev
				{ 53, 79, 68, 0, 0, 0, 24, 0, 137, 109, 56, 89 }, // 8-Zhitomir
				{ 0, 0, 0, 0, 0, 87, 116, 137, 0, 112, 0, 0 }, // 9-Khmelnitskiy
				{ 0, 0, 0, 0, 0, 102, 103, 109, 112, 0, 99, 0 }, // 10-Shepetovka
				{ 0, 0, 0, 0, 0, 0, 0, 56, 0, 99, 0, 117 }, // 11-Novograd-Volynskiy
				{ 0, 55, 0, 0, 0, 0, 0, 89, 0, 0, 117, 0 } // 12-Korosten

		};

		if (startingPoint == finishPoint || startingPoint > cities.length || finishPoint > cities.length)
			return;

		Route route = new Route();
		route.findRoute(startingPoint, finishPoint, cities, routeAndPriceMap);
	}

	public static void listOfCitiesToConsole(String[] listCities) {
		for (int i = 0; i < listCities.length; i++) {
			System.out.printf("%2d%s%s\n", i + 1, ") ", listCities[i]);
		}
	}
}
