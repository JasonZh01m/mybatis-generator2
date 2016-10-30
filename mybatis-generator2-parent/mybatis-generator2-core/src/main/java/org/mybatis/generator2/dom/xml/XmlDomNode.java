package org.mybatis.generator2.dom.xml;

public abstract class XmlDomNode<T> {

    XmlDomNode<?> parent;
    
    public XmlDomNode<?> getParent() {
        return parent;
    }
    
    public abstract T deepCopy();
    
    public abstract void accept(XmlDomVisitor visitor);
}
