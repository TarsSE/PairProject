package cn.edu.fzu.papermanage.dao;

import cn.edu.fzu.papermanage.entity.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperDao extends JpaRepository<Paper,Integer> {
    /**
     * 通过标题查找论文信息
     *
     * @param titleProcessed 要查找的论文题目，可以插入%进行模糊查询
     * @return the list 包含完整信息的Paper列表
     */
    @Query(value = "select DISTINCT * from papers where title like ?1",nativeQuery = true)
    List<Paper> findPapersByTitle(String titleProcessed);

    /**
     * 通过用户id查找其关联的所有论文（不包括关键词）
     *
     * @param userId the user id 用户id
     * @return the list 用户关联的论文列表（不包括关键词）
     */
    @Query(value = "select papers.id,papers.title,papers.source,papers.url,papers.publishyear,papers.abstract " +
            "from papers,user_paper " +
            "where user_paper.userid = ?1 and user_paper.paperId =papers.id;",nativeQuery = true)
    List<Paper> findAllPapersByUserId(Integer userId);
}
