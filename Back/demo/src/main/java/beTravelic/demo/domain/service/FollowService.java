package beTravelic.demo.domain.service;

import beTravelic.demo.domain.dto.FollowSaveRequestDto;
import beTravelic.demo.domain.dto.FollowSaveResponseDto;
import beTravelic.demo.domain.entity.Follow;
import beTravelic.demo.domain.entity.User;
import beTravelic.demo.domain.repository.FollowRepository;
import beTravelic.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    public final FollowRepository followRepository;
    public final UserRepository userRepository;

    public FollowSaveResponseDto followSave(String id, FollowSaveRequestDto dto){
        Follow follow = new Follow();
        User follower = userRepository.findUserById(id).orElseThrow(() ->
                new RuntimeException("일치하는 사용자 없음"));
        User following = userRepository.findById(dto.getId()).orElseThrow(() ->
                new RuntimeException("일치하는 사용자 없음"));

        follow.setFollower(follower);
        follow.setFollowing(following);

        followRepository.save(follow);
        return new FollowSaveResponseDto(follow.getFollow_id());
    }

    public void followDelete(String id, Long followId){
        Follow follower = followRepository.findFollowByFollower(followId).orElseThrow(() ->
                new RuntimeException("일치하는 사용자 없음"));
        User following = userRepository.findUserById(id).orElseThrow(() ->
                new RuntimeException("일치하는 사용자 없음"));
        if(following.getUser_id().equals(follower.getFollowing())){
            followRepository.delete(follower);
        } else {
            new NoPermissionException();
        }
    }


}
