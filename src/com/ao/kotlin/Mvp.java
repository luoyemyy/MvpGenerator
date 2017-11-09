package com.ao.kotlin;

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
        String filename = name + (isActivity ? "Activity.kt" : "Fragment.kt");
        String content = String.format(isActivity ? TemplateMvp.view_activity : TemplateMvp.view_fragment, packageName, name);
        write(filename, content);
    }

    private void createPresenterClass() throws IOException {
        String filename = name + "PresenterImpl.kt";
        String content = String.format(isActivity ? TemplateMvp.presenter_activity : TemplateMvp.presenter_fragment, packageName, name);
        write(filename, content);
    }

    private void createMvpClass() throws IOException {
        String filename = "I" + name + "Mvp.kt";
        String content = String.format(TemplateMvp.mvp, packageName, name);
        write(filename, content);
    }

    private void createModelClass() throws IOException {
        String filename = name + "ModelImpl.kt";
        String content = String.format(TemplateMvp.model, packageName, name);
        write(filename, content);
    }


}
