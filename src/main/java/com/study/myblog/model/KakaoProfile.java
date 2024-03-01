package com.study.myblog.model;

import lombok.Data;

@Data
public class KakaoProfile {

    public Long id;
    public String connectedAt;
    public Properties properties;
    public KakaoAccount kakaoAccount;
    @Data
    public class Properties {

        public String nickname;
        public String profileImage;
        public String thumbnailImage;

    }

    @Data
    public class KakaoAccount {

        public Boolean profileNicknameNeedsAgreement;
        public Boolean profileImageNeedsAgreement;
        public Profile profile;

        @Data
        public class Profile {

            public String nickname;
            public String thumbnailImageUrl;
            public String profileImageUrl;
            public Boolean isDefaultImage;

        }

    }

}






