package org.mybatis.generator2.dom.java;

import java.util.Optional;

public class Parameter extends JavaDomNode {

    private ModifierSet modifierSet;
    private String name;
    private String type;
    private boolean isVarargs;

    private Parameter() {
        super();
    }

    public Optional<ModifierSet> getModifierSet() {
        return Optional.ofNullable(modifierSet);
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public boolean isVarArgs() {
        return isVarargs;
    }
    
    @Override
    public void accept(JavaDomVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public JavaNodeType getNodeType() {
        return JavaNodeType.PARAMETER;
    }
    
    @Override
    public boolean allowsModifier(JavaModifier javaModifier) {
        return javaModifier == JavaModifier.FINAL;
    }

    public static Parameter of(String type, String name) {
        return new Builder(type, name).build();
    }

    public static class Builder {
        private Parameter parameter = new Parameter();
        
        public Builder(String type, String name) {
            parameter.type = type;
            parameter.name = name;
        }

        public Builder withModifier(JavaModifier javaModifier) {
            parameter.getModifierSet().orElseGet(() -> {
                ModifierSet ms = new ModifierSet(parameter);
                parameter.modifierSet = ms;
                return ms;
            }).javaModifiers.add(javaModifier);
            
            return this;
        }
        
        public Builder isVarArgs(boolean isVarArgs) {
            parameter.isVarargs = isVarArgs;
            return this;
        }
        
        public Parameter build() {
            return parameter;
        }
    }
}
