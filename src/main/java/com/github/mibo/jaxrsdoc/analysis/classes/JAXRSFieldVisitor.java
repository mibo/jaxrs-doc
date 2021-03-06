package com.github.mibo.jaxrsdoc.analysis.classes;

import com.github.mibo.jaxrsdoc.analysis.classes.annotation.DefaultValueAnnotationVisitor;
import com.github.mibo.jaxrsdoc.analysis.classes.annotation.ParamAnnotationVisitor;
import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.rest.MethodParameter;
import com.github.mibo.jaxrsdoc.model.rest.ParameterType;
import com.github.mibo.jaxrsdoc.model.rest.TypeIdentifier;
import com.github.mibo.jaxrsdoc.model.results.ClassResult;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author Sebastian Daschner
 */
class JAXRSFieldVisitor extends FieldVisitor {

    private final ClassResult classResult;
    private final String signature;
    private MethodParameter parameter;

    JAXRSFieldVisitor(final ClassResult classResult, final String desc, final String signature) {
        super(Opcodes.ASM5);
        this.classResult = classResult;
        this.signature = signature == null ? desc : signature;
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
        switch (desc) {
            case Types.PATH_PARAM:
                return paramAnnotationVisitor(ParameterType.PATH);
            case Types.QUERY_PARAM:
                return paramAnnotationVisitor(ParameterType.QUERY);
            case Types.HEADER_PARAM:
                return paramAnnotationVisitor(ParameterType.HEADER);
            case Types.FORM_PARAM:
                return paramAnnotationVisitor(ParameterType.FORM);
            case Types.COOKIE_PARAM:
                return paramAnnotationVisitor(ParameterType.COOKIE);
            case Types.MATRIX_PARAM:
                return paramAnnotationVisitor(ParameterType.MATRIX);
            case Types.DEFAULT_VALUE:
                return defaultAnnotationVisitor();
            default:
                return null;
        }
    }

    private AnnotationVisitor paramAnnotationVisitor(final ParameterType parameterType) {
        if (parameter == null)
            parameter = new MethodParameter(TypeIdentifier.ofType(signature), parameterType);
        else
            parameter.setParameterType(parameterType);
        return new ParamAnnotationVisitor(parameter);
    }

    private AnnotationVisitor defaultAnnotationVisitor() {
        if (parameter == null)
            parameter = new MethodParameter(TypeIdentifier.ofType(signature));
        return new DefaultValueAnnotationVisitor(parameter);
    }

    @Override
    public void visitEnd() {
        if (parameter != null)
            classResult.getClassFields().add(parameter);
    }

}
