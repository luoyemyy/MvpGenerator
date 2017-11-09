package com.ao.kotlin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Creator {
    protected String path;
    protected String packageName;
    protected boolean isActivity;
    protected String name;

    public Creator(String path, String packageName, boolean isActivity, String name) {
        this.path = path;
        this.packageName = packageName;
        this.isActivity = isActivity;
        this.name = name;
    }


    protected void write(String fileName, String content) throws IOException {
        File file = new File(path, fileName);
        if (!file.exists() && file.createNewFile()) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.flush();
            writer.close();
        }
    }
}
