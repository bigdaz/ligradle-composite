package org.sample;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.api.Action;
import org.gradle.api.Task;

import java.util.LinkedHashMap;

public class LiGradleJvmPlugin implements Plugin<Project> {

    public void apply(Project project) {
        project.getPluginManager().apply(org.gradle.api.plugins.JavaPlugin.class);
        project.getPluginManager().apply(org.gradle.plugins.ide.idea.IdeaPlugin.class);
        project.getPluginManager().apply(org.gradle.api.publish.maven.plugins.MavenPublishPlugin.class);

        project.getTasks().create("precompileAll", PreCompile.class);
        project.getTasks().withType(JavaCompile.class, new Action<Task>() {
            @Override
            public void execute(Task task) {
                task.dependsOn("precompileAll");
            }
        });
    }

}
