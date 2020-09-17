package com.javamapper.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AverageAndCountOfRating {
	public static class Pair<T1,T2>{
		T1 item1;
		T2 item2;
		Pair(T1 item1,T2 item2){
			this.item1=item1;
			this.item2=item2;
		}
	}
    public static class RatingStatisticsCollectorImpl implements RatingStatisticsCollector {
    	Map<String,Pair<Integer,Integer>> ratingMap=new HashMap<>();
        @Override
        public void putNewRating(String app, int rating){
        	if(ratingMap.containsKey(app)) {
        		Pair<Integer, Integer> pairValue = ratingMap.get(app);
        		pairValue.item1=pairValue.item1+1;
        		pairValue.item2=pairValue.item2+rating;
        		ratingMap.put(app,pairValue);
        	}else {
            ratingMap.put(app,new Pair<Integer,Integer>(1,rating));
        	}
        }

        @Override
        public double getAverageRating(String app){
        	Optional<Entry<String, Pair<Integer,Integer>>> filteredEntry = ratingMap.entrySet().stream().filter(entry->entry.getKey().equals(app)).findAny();
        	if(filteredEntry.isPresent()) {
        		Pair<Integer, Integer> pairValue = filteredEntry.get().getValue();
        		return pairValue.item2/(double)pairValue.item1;
        	}
        	return 0;
        }

        @Override
        public int getRatingsCount(String app){
        	Optional<Entry<String, Pair<Integer,Integer>>> filteredEntry = ratingMap.entrySet().stream().filter(entry->entry.getKey().equals(app)).findAny();
        	if(filteredEntry.isPresent()) {
        		Pair<Integer, Integer> pairValue = filteredEntry.get().getValue();
        		return pairValue.item1;
        	}
			return 0;
        }
    }

    ////////////////// DO NOT MODIFY BELOW THIS LINE ///////////////////

    public interface RatingStatisticsCollector {
        // Ratings feed will call this method when new app rating information is received. This is input to your class. ratings is a non negative integer between 0 to 10.
        public void putNewRating(String app, int rating);

        // Report the average rating of the app. 
        public double getAverageRating(String app);

        // Report the total number of rating for an app.
        public int getRatingsCount(String app);
    }

	public void problem1() {
		Scanner scanner = new Scanner(System.in);
		int numLines = 1;// Integer.parseInt(scanner.nextLine());
		int currentLine = 0;
		while (currentLine++ < numLines) {
			final RatingStatisticsCollector stats = new RatingStatisticsCollectorImpl();
			final Set<String> apps = new TreeSet<>();

			String line = "gmail 5,facebook 9,gmail 6,facebook 11,linkedin 4,gmail 3";// scanner.nextLine();
			String[] inputs = line.split(",");
			for (int i = 0; i < inputs.length; ++i) {
				String[] tokens = inputs[i].split(" ");
				final String app = tokens[0];
				apps.add(app);
				final int runs = Integer.parseInt(tokens[1]);
				stats.putNewRating(app, runs);
				System.out.print(""+app+":"+runs+"  ");
			}
			System.out.println();
			for (String app : apps) {
				System.out.println(
						String.format("App:%s\tAverageRating:%.2f\tRatingCount: %d", app, stats.getAverageRating(app), stats.getRatingsCount(app)));
			}

		}
		scanner.close();
	}
}