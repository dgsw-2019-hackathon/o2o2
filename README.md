## [심사기준](./Evaluation.md)

### 참고 사항

- 각 팀은 [해커톤 전용 Organization](https://github.com/dgsw-2019-hackathon)에서 하나의 팀을 사용하실 수 있습니다.
  - 팀명은 `dgsw_team_(num)` 형식으로 임의 지정되어 만들어져 있으며, 추후 자신이 원하는 팀명으로 변경할 수 있습니다. (Github 특성상 한글 불가능)
  - 현재 콜라보레이터로 레포지토리에 초대돼있는 사람이 많습니다.
    통계를 위해 자신의 팀 설정에서 초대해주세요.
    ![image](https://user-images.githubusercontent.com/32216112/61262801-91505b00-a7c1-11e9-9d24-adf01835df33.png)

  
 단, **변경 시 `(num)_teamname` 형식으로 변경**해주시기 바랍니다.
  - 팀장은 팀에 초대된 뒤 자신의 팀원들을 **전부** 초대해 주세요 (Github 계정이 없으면 생성해서 초대해 주세요)
- 레포지토리는 자유 생성이 가능합니다.
  - 레포지토리를 생성하고 난 후 팀에서 레포지토리를 연결하는 방식으로 사용해주시기 바랍니다.
- 본 Github 사용에 궁금한 사항이 있으면 언제든지 [스태프 서상희](https://www.facebook.com/profile.php?id=100010478115976), [스태프 남현욱](https://www.facebook.com/hw0k.nam)에게 문의 주시기 바랍니다.

### Git 기본 사용법

- 참고: [https://backlog.com/git-tutorial/kr/](https://backlog.com/git-tutorial/kr/)

- clone

  - `git clone [레포지토리 git 주소]`
  - Remote 레포지토리를 로컬에 복사하는 작업
  - 레포지토리 git 주소는 아래 버튼으로 복사 가능
  - ![wow](https://github.com/dgsw-2019-hackathon/dgsw-2019-hackathon.github.io/blob/master/images/1.PNG?raw=true)

- add

  - `git add [. or * or 추가할 파일명]`
  - 변경점이 있는 파일을 Stage하는 작업

- commit

  - `git commit -m "커밋 메시지"`
  - Staged 된 파일들로 새로운 커밋을 만드는 작업

- push

  - `git push [remote] [branch]`
  - 예시: `git push origin master`
  - 로컬의 커밋들을 업로드하여 `remote`와 맞추는 작업

- pull

  - `git pull [remote] [branch]`
  - 예시: `git pull origin master`
  - `remote`의 커밋을 내려받아 로컬과 맞추는 작업
