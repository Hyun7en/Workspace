import React from 'react'
import Comment from './Comment'

const commnets = [
    {
        name : "최지원",
        comment: "안녕하세요. 최지원입니다. 잘부탁드려요",
        path: "https://i.namu.wiki/i/vrrxAFOllLfXJv81KRPgEGzr4zZEdOvqDbV8g7pvrKEW74ffiCt6jquf1oEvwTM3z_4lI0om7iQuzdAELZ8Qlg.webp"
    },{
        name : "김효영",
        comment: "잘 모르겠어요^^",
        path: "https://image2.gnsister.com/images/document/1676517921901_d04609d93dfa4bc596f48d50ecbb5d55.jpg?originalImageWidth=633&originalImageHeight=626"
    },{
        name : "김지원",
        comment: "화이팅~ 잘 해보세요~",
        path: "https://i.ytimg.com/vi/z-FGKPVGv9s/maxresdefault.jpg"
    }
]
// =>

const CommentList = () => {
  return (
    <div>
        {commnets.map(comment => {
            return <Comment name={comment.name} comment={comment.comment} path={comment.path} />
        })}
    </div>
  )
}

export default CommentList