package org.sample;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class PreCompile extends DefaultTask {

    @TaskAction
    public void precompile() {
        System.out.println("Precompiling...");
    }
}
