package wxgaly.android.annotationcompiler.util;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * wxgaly.android.annotationcompiler.util.
 *
 * @author Created by WXG on 2019/3/1 001 17:30.
 * @version V1.0
 */
public class AnnotatedClass {

    private static class TypeUtil {
        static final ClassName BINDER = ClassName.get("wxgaly.android.annotationapi.api", "ViewBinder");
        static final ClassName PROVIDER = ClassName.get("wxgaly.android.annotationapi.api", "ViewFinder");
    }

    private TypeElement mTypeElement;
    private ArrayList<BindViewField> mFields;
    private Elements mElements;

    AnnotatedClass(TypeElement typeElement, Elements elements) {
        mTypeElement = typeElement;
        mElements = elements;
        mFields = new ArrayList<>();
    }

    void addField(BindViewField field) {
        mFields.add(field);
    }

    JavaFile generateFile() {

        TypeSpec.Builder injectClassBuilder = TypeSpec.classBuilder(mTypeElement.getSimpleName() + "$$ViewBinder");

        //generateMethod
        MethodSpec.Builder bindViewMethod = MethodSpec.methodBuilder("bindView")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(mTypeElement.asType()), "host")
                .addParameter(TypeName.OBJECT, "source")
                .addParameter(TypeUtil.PROVIDER, "finder");

        for (BindViewField field : mFields) {
            // find views
            bindViewMethod.addStatement("host.$N = ($T)(finder.findView(source, $L))", field.getFieldName(),
                    ClassName.get(field.getFieldType()), field.getResId());
            //创建变量
            String fieldView = "view" + Integer.toHexString(field.getResId());
            FieldSpec fieldSpec = FieldSpec.builder(ClassName.get(field.getFieldType()),
                    fieldView).addModifiers(Modifier.PRIVATE).build();
            injectClassBuilder.addField(fieldSpec);

            bindViewMethod.addStatement("$N = host.$N", fieldView, field.getFieldName());

            TypeSpec doClick = TypeSpec.anonymousClassBuilder("")
                    .addSuperinterface(
                            ClassName.get("wxgaly.android.annotationapi.api", "DebouncingOnClickListener")
                    )
                    .addMethod(MethodSpec.methodBuilder("doClick")
                            .addAnnotation(Override.class)
                            .addModifiers(Modifier.PUBLIC)
                            .addParameter(ClassName.get("android.view", "View"), "v")
                            .build())
                    .build();

            bindViewMethod.addStatement("$N.setOnClickListener($L)", fieldView, doClick);
        }

        MethodSpec.Builder unBindViewMethod = MethodSpec.methodBuilder("unBindView")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(mTypeElement.asType()), "host")
                .addAnnotation(Override.class);
        for (BindViewField field : mFields) {
            unBindViewMethod.addStatement("host.$N = null", field.getFieldName());
        }
        unBindViewMethod.addStatement("host = null");

        //generaClass

        injectClassBuilder.addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.BINDER, TypeName.get(mTypeElement.asType())))
                .addMethod(bindViewMethod.build())
                .addMethod(unBindViewMethod.build());

        String packageName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();

        return JavaFile.builder(packageName, injectClassBuilder.build()).build();
    }

}
