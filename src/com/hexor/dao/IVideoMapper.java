package com.hexor.dao;

import com.hexor.repo.Pager;
import com.hexor.repo.VideoBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("com.hexor.dao.IVideoMapper")
public interface IVideoMapper {
	//mysql��ҳ��ѯ
	public List<VideoBean> limit(Pager pager);
	//��ѯ������
	public long getVideosCount();
}
