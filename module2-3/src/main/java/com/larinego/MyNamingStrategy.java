package com.larinego;


import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.io.Serializable;
import java.util.Locale;

public class MyNamingStrategy extends PhysicalNamingStrategyStandardImpl implements Serializable {

    final static String TABLE_PREFIX = "T_";
    final static String COLUMN_PREFIX = "C_";



    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier(addPrefixTable(addUnderscores(name.getText())), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return new Identifier(addPrefixColumn(addUnderscores(name.getText())), name.isQuoted());
    }


    protected static String addUnderscores(String name) {
        final StringBuilder buf = new StringBuilder( name.replace('.', '_') );
        for (int i=1; i<buf.length()-1; i++) {
            if (
                    Character.isLowerCase( buf.charAt(i-1) ) &&
                            Character.isUpperCase( buf.charAt(i) ) &&
                            Character.isLowerCase( buf.charAt(i+1) )
                    ) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase(Locale.ROOT);
    }

    protected static String addPrefixTable(String name) {
        return TABLE_PREFIX + name;
    }

    protected static String addPrefixColumn(String name) {
        return COLUMN_PREFIX + name;
    }
}