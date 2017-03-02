package com.codolutions.starterapp.web;

import com.codolutions.starterapp.domain.Comment;
import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

class React {

    private ThreadLocal<NashornScriptEngine> engineHolder = new ThreadLocal<NashornScriptEngine>() {
        @Override
        protected NashornScriptEngine initialValue() {
            NashornScriptEngine nashornScriptEngine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName(
                    "nashorn");
            try {
                nashornScriptEngine.eval(read("static/js/nashorn-polyfill.js"));
                nashornScriptEngine.eval(read("static/js/bower_components/react/react.min.js"));
                nashornScriptEngine.eval(read("static/js/bower_components/showdown/compressed/Showdown.min.js"));
                nashornScriptEngine.eval(read("static/js/gen/commentBox.js"));
            }
            catch (ScriptException e) {
                throw new RuntimeException(e);
            }
            return nashornScriptEngine;
        }
    };

    String renderCommentBox(List<Comment> comments) {
        try {
            Object html = engineHolder.get().invokeFunction("renderServer", comments);
            return String.valueOf(html);
        }
        catch (Exception e) {
            throw new IllegalStateException("failed to render react component", e);
        }
    }

    private Reader read(String path) {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);
        return new InputStreamReader(in);
    }
}
