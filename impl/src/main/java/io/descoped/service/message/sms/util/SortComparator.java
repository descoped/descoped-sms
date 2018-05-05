/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class SortComparator implements Comparator<Object> {

    public String fieldName;

    public SortComparator(String fieldName) {
        this.fieldName = fieldName;
    }

    public int compare(Object o1, Object o2) {
        try {

            Object val1 = PropertyUtils.getProperty(o1, fieldName);
            Object val2 = PropertyUtils.getProperty(o2, fieldName);

            if (val1.equals(val2)) {
                return 0;
            }

            if (val1 instanceof String) {
                return ((String) val1).compareTo((String) val2);
            }
            if (val1 instanceof Integer) {
                Integer int1 = new Integer((String) val1);
                Integer int2 = new Integer((String) val2);

                return int1.compareTo(int2);
            }
            if (val1 instanceof java.util.Date) {
                java.util.Date date1 = (java.util.Date) val1;
                java.util.Date date2 = (java.util.Date) val2;
                return date1.compareTo(date2);
            }

        } catch (IllegalAccessException e) {
            throw new SortException(e);
        } catch (InvocationTargetException e) {
            throw new SortException(e);
        } catch (NoSuchMethodException e) {
            throw new SortException(e);
        }

        return 0;
    }
}

