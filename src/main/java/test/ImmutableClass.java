package test;

import java.util.Collections;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ImmutableClass {
    private final String name;
    private final List<String> list;

    public ImmutableClass(String name, List<String> list) {
        this.name = name;
        // Creating a defensive copy of the list to ensure immutability
        this.list = new ArrayList<>(list);
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        // Returning an unmodifiable view of the list to ensure immutability
        return Collections.unmodifiableList(list);
    }

}
