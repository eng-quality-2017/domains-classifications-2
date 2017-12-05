package domainClassification2;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtAssignment;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;

public class DomainClass {
    public static void main(String [] args){

        Launcher launcher = new Launcher();
        launcher.addInputResource("src/main/java/bgu/testing/domainClassification2/domainClassification2.SpoonExample.java");
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);
        launcher.buildModel();
        CtModel model = launcher.getModel();
        List<CtMethod> methods = model.getElements(new TypeFilter<>(CtMethod.class));
        CtMethod codeDomain = methods.get(1);
        List<CtStatement> statements = codeDomain.getBody().getStatements();
        for (CtStatement statement : statements) {
            System.out.println("Analyzing statement at line " + statement.getPosition().getLine());
            if(statement instanceof CtIf) {
                CtIf ifStatement = (CtIf) statement;
                System.out.println("If statement: " + ifStatement.getCondition());
            }
            if(statement instanceof CtLocalVariable) {
                CtLocalVariable localVariable = (CtLocalVariable) statement;
                System.out.println("Local variable declaration: " + localVariable.getReference() + " of type " + localVariable.getType());
            }
            if(statement instanceof CtAssignment) {
                CtAssignment assignment = (CtAssignment) statement;
                System.out.println("Assigning variable " + assignment.getAssigned() + " with expression/value: " + assignment.getAssignment());
            }
        }

    }
}
