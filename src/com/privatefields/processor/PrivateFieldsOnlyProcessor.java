package com.privatefields.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes({"com.privatefields.annotation.PrivateFieldsOnly"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PrivateFieldsOnlyProcessor extends AbstractProcessor {

	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		
		
//		 for (TypeElement ann : annotations) {
//             Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(ann);
//
//             for (Element ae : annotatedElements) {
//                 List<? extends Element> innerElements = ae.getEnclosedElements();
//
//                 for (Element ie : innerElements) {
//                     Set<Modifier> modifiers = ie.getModifiers();
//                     if (modifiers.contains(Modifier.PUBLIC)) {
//                         messager.printMessage(Kind.ERROR,
//                                 "Incapsulation violation. Make all public fields private or protected", ie);
//                     }
//                 }
//             }
//         }
//       return true;
//	}

		for (TypeElement typeElement : annotations) {
			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(typeElement);

			for (Element element : annotatedElements) {
				Set<Modifier> modifiers = element.getModifiers();
				if (modifiers.contains(Modifier.PUBLIC)) messager.printMessage(Kind.ERROR,
							"Incapsulation violation. Make all public fields private or protected", element);
			}
		}
		return true;
	}

}
