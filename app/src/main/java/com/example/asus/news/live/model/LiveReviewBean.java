package com.example.asus.news.live.model;

/**
 * Created by ASUS on 2016/11/2.
 */

public  class LiveReviewBean {
    private String startTime;
    private int roomId;
    /**
     * timg : http://dingyue.nosdn.127.net/P5VmaEhzV4sWrIr3Iwg6LII3SnaqHIW7gayOhbTo=RW4z1477467264981.jpg
     * tcount : 1748
     * tid : T1477467265470
     * tname : 小蜂财经
     */

    private SourceinfoBean sourceinfo;
    private boolean pano;
    private int liveType;
    private String roomName;
    private String source;
    private int liveStatus;
    private boolean mutilVideo;
    private String image;
    private int confirm;
    private int type;
    private int userCount;
    private boolean video;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public SourceinfoBean getSourceinfo() {
        return sourceinfo;
    }

    public void setSourceinfo(SourceinfoBean sourceinfo) {
        this.sourceinfo = sourceinfo;
    }

    public boolean isPano() {
        return pano;
    }

    public void setPano(boolean pano) {
        this.pano = pano;
    }

    public int getLiveType() {
        return liveType;
    }

    public void setLiveType(int liveType) {
        this.liveType = liveType;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(int liveStatus) {
        this.liveStatus = liveStatus;
    }

    public boolean isMutilVideo() {
        return mutilVideo;
    }

    public void setMutilVideo(boolean mutilVideo) {
        this.mutilVideo = mutilVideo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static class SourceinfoBean {
        private String timg;
        private int tcount;
        private String tid;
        private String tname;

        public String getTimg() {
            return timg;
        }

        public void setTimg(String timg) {
            this.timg = timg;
        }

        public int getTcount() {
            return tcount;
        }

        public void setTcount(int tcount) {
            this.tcount = tcount;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }
    }
}
