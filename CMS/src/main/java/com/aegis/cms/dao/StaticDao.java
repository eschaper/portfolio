package com.aegis.cms.dao;

import com.aegis.cms.model.StaticContent;

public interface StaticDao {

    public void addStaticContent(StaticContent cont);

    public StaticContent getStaticContent();

    public void updateStaticContent(StaticContent cont);

    public void deleteStaticContent(StaticContent cont);
}
