package com.example.datajpaa.repository;

public interface NestedClosedProjections {

    String getUsername();

    TeamInfo getTeam();

    interface TeamInfo {
        String getName();
    }
}
