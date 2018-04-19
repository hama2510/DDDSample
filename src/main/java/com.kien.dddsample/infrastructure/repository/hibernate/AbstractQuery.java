/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kien.dddsample.infrastructure.repository.hibernate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

public interface AbstractQuery {
    public Expression createExpression(CriteriaBuilder builder, Root root);
}
