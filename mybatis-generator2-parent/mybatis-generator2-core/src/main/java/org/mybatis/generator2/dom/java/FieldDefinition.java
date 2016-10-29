package org.mybatis.generator2.dom.java;

import java.util.Optional;

public class FieldDefinition extends JavaDomNode {

    private JavaDoc javaDoc;
    private ModifierSet modifierSet = new ModifierSet(this);
    private String type;
    private String name;
    private String initializationString;
    
    private FieldDefinition() {
        super();
    }

    @Override
    public void accept(JavaDomVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public JavaNodeType getNodeType() {
        return JavaNodeType.FIELD;
    }

    @Override
    public boolean allowsModifier(JavaModifier javaModifier) {
        boolean rc;
        switch (parent.getNodeType()) {
        case CLASS:
        case ENUM:
            rc = isModifierAllowedForClassField(javaModifier);
            break;
        
        case INTERFACE:
            rc = isModifierAllowedForInterfaceField(javaModifier);
            break;
            
        default:
            rc = false;
        }

        return rc;
    }
    
    private boolean isModifierAllowedForClassField(JavaModifier javaModifier) {
        boolean rc;
        
        switch (javaModifier) {
        case PUBLIC:
        case PROTECTED:
        case PRIVATE:
        case STATIC:
        case FINAL:
        case TRANSIENT:
        case VOLATILE:
            rc = true;
            break;
        
        default:
            rc = false;
        }
        
        return rc;
    }

    private boolean isModifierAllowedForInterfaceField(JavaModifier javaModifier) {
        // interface fields are implicitly public, static, final.  No need to specify that.
        return false;
    }

    public Optional<JavaDoc> getJavaDoc() {
        return Optional.ofNullable(javaDoc);
    }
    
    public ModifierSet getModifierSet() {
        return modifierSet;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getInitializationString() {
        return Optional.ofNullable(initializationString);
    }
    
    public static class Builder {
        private FieldDefinition fieldDefinition = new FieldDefinition();

        public Builder(String type, String name) {
            fieldDefinition.type = type;
            fieldDefinition.name = name;
        }
        
        public Builder withInitializationString(String initializationString) {
            fieldDefinition.initializationString = initializationString;
            return this;
        }
        
        public Builder withModifier(JavaModifier javaModifier) {
            fieldDefinition.getModifierSet().addJavaModifier(javaModifier);
            return this;
        }
        
        public Builder withJavaDoc(JavaDoc javaDoc) {
            fieldDefinition.javaDoc = javaDoc;
            return this;
        }
        
        public FieldDefinition build() {
            return fieldDefinition;
        }
    }
}
