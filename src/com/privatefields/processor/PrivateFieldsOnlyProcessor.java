package com.privatefields.processor;

import java.util.List;
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

@SupportedAnnotationTypes({ "com.privatefields.annotation.PrivateFieldsOnly" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PrivateFieldsOnlyProcessor extends AbstractProcessor {

	private Messager messager;

	@Override
	public void init(ProcessingEnvironment env) {
		messager = env.getMessager();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

		for (TypeElement typeElement : annotations) {
			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(typeElement);

			for (Element aElement : annotatedElements) {
				List<? extends Element> innerElements = aElement.getEnclosedElements();

				for (Element iElement : innerElements) {
					Set<Modifier> modifiers = iElement.getModifiers();
					if (modifiers.contains(Modifier.PUBLIC)) {
						messager.printMessage(Kind.ERROR,
								"Incapsulation violation. Make all public fields private or protected", iElement);
					}
				}
			}
		}
		return true;
	}

}
