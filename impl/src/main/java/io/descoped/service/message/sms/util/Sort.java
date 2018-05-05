/***************************************************************************
 * Copyright 2003-2004 Ove Ranheim         All rights reserved.  *
 * Please look at license.txt in info directory for more license detail.   *
 **************************************************************************/
package io.descoped.service.message.sms.util;

import java.util.*;

/**
 * @author: Ove Ranheim
 * @email: oranheim@descoped.io
 */
public class Sort {

    public static final int ASC = 0;
    public static final int DESC = 1;

    public Sort() {
    }

    public static List<TestBean> sort(List<TestBean> list, String fieldName, int order) {

        Collections.sort(list, new SortComparator(fieldName));
        if (order == Sort.DESC) Collections.reverse(list);

        return list;
    }

    public static void main(String[] args) {
        ArrayList<TestBean> list = new ArrayList<TestBean>();
        list.add(new TestBean("ove", "toyen"));
        list.add(new TestBean("tuva", "lokka"));
        list.add(new TestBean("knut", "st.haugen"));
        list.add(new TestBean("tor-egil", "Ljan"));
        List<TestBean> sortedList = Sort.sort(list, "name", Sort.DESC);
        for (Iterator<TestBean> iterator = sortedList.iterator(); iterator.hasNext(); ) {
            TestBean myBean = iterator.next();
            System.out.println(myBean.getName());
        }
    }

    @SuppressWarnings("unchecked")
    static public void putInSorted(List<TestBean> a, Comparator c) {
        int cnt = 0;
        for (Iterator<TestBean> iterator = a.iterator(); iterator.hasNext(); ) {
            TestBean temp = iterator.next();

            int j = cnt++ - 1;
            for (; j >= 0 && c.compare(temp, a.get(j)) < 0; j--)
                a.set(j + 1, a.get(j));

            a.set(j + 1, temp);

        }
    }

}

