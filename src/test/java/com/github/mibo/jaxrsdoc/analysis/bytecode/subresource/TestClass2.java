package com.github.mibo.jaxrsdoc.analysis.bytecode.subresource;

import javax.ws.rs.container.ResourceContext;
import java.util.Set;

import static java.util.Collections.singleton;

public class TestClass2 {

    public Object method() {
        ResourceContext rc = null;
        return rc.getResource(SubResource.class);
    }

    public static Set<String> getResult() {
        return singleton("com/github/mibo/jaxrsdoc/analysis/bytecode/subresource/TestClass2$SubResource");
    }

    private class SubResource {
    }

}
