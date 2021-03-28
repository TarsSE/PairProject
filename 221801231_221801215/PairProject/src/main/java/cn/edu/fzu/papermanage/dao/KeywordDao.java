package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordDao extends JpaRepository<Keyword,Integer> {

    /**
     * 根据用户id获取其关联论文的出现频率最高的前十个关键词
     *
     * @param userId the user id 用户id
     * @return the list 出现频率最高的前十个关键词列表，按频率由大到小排列
     */
    @Query(value = "select distinct keywords.keyword " +
            "from papers,user_paper,keywords " +
            "where user_paper.userId = ?1 " +
            "and user_paper.paperId = papers.id " +
            "and papers.id = keywords.paperId " +
            "group by keywords.keyword " +
            "order by count(keyword) desc " +
            "limit 10",nativeQuery = true)
    List<String> findTopTenFrequencyKeywordByUserId(Integer userId);

}