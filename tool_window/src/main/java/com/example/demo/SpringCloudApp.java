/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.example.demo;

import com.example.demo.operable.Operable;

public class SpringCloudApp implements Operable {
    private String name;
    private Status status = Status.STARTED;

    public SpringCloudApp(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    public SpringCloudApp(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void start() {
        this.status = Status.STARTED;
    }

    @Override
    public void stop() {
        this.status = Status.STOPPED;
    }

    @Override
    public void restart() {
        this.status = Status.STOPPED;
        this.status = Status.STARTED;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("%s[%s]", this.getName(), this.getStatus());
    }
}
