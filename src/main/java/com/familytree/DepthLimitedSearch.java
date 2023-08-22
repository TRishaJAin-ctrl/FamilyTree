package com.familytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DepthLimitedSearch {
    public static Optional<List<Person>> search(String name, Person root, int depthLimit) {
        Set<Person> visited = new HashSet<>();

        return depthLimitedSearch(name, root, depthLimit, visited);
    }

    private static Optional<List<Person>> depthLimitedSearch(String name, Person currentPerson,
                                                             int depthLimit, Set<Person> visited) {
        if (depthLimit == 0) {
            return Optional.empty();
        }

        visited.add(currentPerson);

        if (currentPerson.getName().equals(name)) {
            List<Person> pathToPerson = new ArrayList<>();
            pathToPerson.add(currentPerson);
            return Optional.of(pathToPerson);
        } else {
            Set<Person> neighbors = currentPerson.getNeighbors();
            for (Person n : neighbors) {
                if (!visited.contains(n)) {
                    Optional<List<Person>> result = depthLimitedSearch(name, n, depthLimit - 1, visited);
                    if (result.isPresent()) {
                        List<Person> pathToPerson = new ArrayList<>(result.get());
                        pathToPerson.add(0, currentPerson);
                        return Optional.of(pathToPerson);
                    }
                }
            }
            return Optional.empty();
        }
    }
}
