package com.ao.java;

import java.io.IOException;

public class Mvp extends Creator{

    public Mvp(String path, String packageName, boolean isActivity, String name) {
        super(path, packageName, isActivity, name);
    }

    public void create() {
        try {
            createMvpClass();
            createViewClass();
            createPresenterClass();
            createModelClass();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createViewClass() throws IOException {
        String filename = name + (isActivity ? "Activity.java" : "Fragment.java");
        String content = String.format(isActivity ? TemplateMvp.view_activity : TemplateMvp.view_fragment, packageName, name);
        write(filename, content);
    }

    private void createPresenterClass() throws IOException {
        String filename = name + "PresenterImpl.java";
        String content = String.format(isActivity ? TemplateMvp.presenter_activity : TemplateMvp.presenter_fragment, packageName, name);
        write(filename, content);
    }

    private void createMvpClass() throws IOException {
        String filename = "I" + name + "Mvp.java";
        String content = String.format(TemplateMvp.mvp, packageName, name);
        write(filename, content);
    }

    private void createModelClass() throws IOException {
        String filename = name + "ModelImpl.java";
        String content = String.format(TemplateMvp.model, packageName, name);
        write(filename, content);
    }


}
