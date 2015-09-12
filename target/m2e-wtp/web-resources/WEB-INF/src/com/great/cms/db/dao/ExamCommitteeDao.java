package com.great.cms.db.dao;

import com.great.cms.db.entity.ExamCommittee;

public interface ExamCommitteeDao extends GenericDao<ExamCommittee, Integer> {
      public ExamCommittee findBySession(int session);
}
