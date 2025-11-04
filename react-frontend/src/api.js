const API_URL = "http://localhost:8080/posts";

export const getPosts = async () => {
  const res = await fetch(API_URL);
  if (!res.ok) throw new Error("게시글 불러오기 실패");
  return await res.json();
};

export const createPost = async (post) => {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(post),
  });
  if (!res.ok) throw new Error("게시글 생성 실패");
  return await res.json();
};

export const updatePost = async (id, updatedPost) => {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(updatedPost),
  });
  if (!res.ok) throw new Error("게시글 수정 실패");
};

export const deletePost = async (id) => {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("게시글 삭제 실패");
};
