#include <stdio.h>
#include <stdlib.h>

//#define log(root, n,X)\
//{\
//if (root != NULL)\
//{\
//	log(root->right, n + 3);\
//	for (int i = 0; i < n; i++)\
//		printf(" ");\
//	printf("%c", *(char*)(root->value));\
//	log(root->left, n + 3);\
//}\
//}\

typedef	struct node {
	int key;
	void* value;
	int size;
	struct treeNode* left;
	struct treeNode* right;
	
} node;

void add(node** root, int key, void* value, unsigned int size) {
	
	if (*root == NULL) {
		*root = malloc(sizeof(node));
		(*root)->left = (*root)->right = NULL;
		(*root)->key = key;
		(*root)->value = malloc(size);
		memcpy((*root)->value, value, size);
		return;
	}

	if (key > (*root)->key) 
		add(&((*root)->right), key, value, size);
	else if (key < (*root)->key) 
		add(&((*root)->left), key, value, size);
	
}

node* search(node* root, int key) {

	node* ret = NULL;
	if (root == NULL) return NULL;
	if (key > root->key) {
			ret = search(root->right, key);
	}
	else if (key < root->key) {
			ret = search(root->left, key);
	}
	else ret = root;

	return ret;

}

node* ptrToRoot(node* root) {
	node* parent = (node*)malloc(sizeof(node));
	parent->key = 0;
	parent->value = NULL;
	parent->left = root;
	parent->right = root;
}

void delete(node** root, int key) {
	//search
	node* found = root;
	node* parent = ptrToRoot(root);
	int isNotFound = 1;
	while (isNotFound) {
		if (found == NULL) return;
		if (found->key == key) isNotFound = 0;
		else {
			parent = found;
			if (key > found->key)
				found = found->right;
			else
				found = found->left;
		}
	}
	//no children
	if (found->left == NULL && found->right == NULL) {
		if (found == root) *root = NULL;
		else {
			if (parent->key > found->key) parent->left = NULL;
			else parent->right = NULL;
		}
		free(found);
	}
	//only left
	else if (found->right == NULL) {
		if (found == root) {
			*root = (*root)->left;
			free(found);
			return;
		}
		if (parent->key > found->key) {
			parent->left = found->left;
		}
		else {
			parent->right = found->left;
		}
		free(found);
	}
	//two children
	else {
		node* curr = found->right;
		while (curr->left != NULL) curr = curr->left;
		if (curr == found->right) {
			curr->left = found->left;
			if (parent->key > found->key) parent->left = curr;
			else parent->right = curr;
		}
		else {
			delete(root,curr->key);
			found->key = curr->key;
			found->value = curr->value;
		}
		free(found);
	}
}

void log(node* root,int n)
{
if (root != NULL)
{
log(root->right, n + 3);
for (int i = 0; i < n; i++)
	printf(" ");
	printf("%c\n", *(char*)(root->value));
	log(root->left, n + 3);
}
}

void preorder(node* root) {
	if (root != NULL) {
		printf("%c", root->value);
		preorder(root->left);
		preorder(root->right);
	}
}

void postorder(node* root) {
	if (root != NULL) {
		postorder(root->left);
		postorder(root->right);
		printf("%c", root->value);

	}
}

void inorder(node* root) {
	if (root != NULL) {
		if (root->value =='+' || root->value =='-')printf("(");
		inorder(root->left);
		printf("%d", *(int *)(root->value));
		inorder(root->right);
		if (root->value == '+' || root->value == '-')printf(")");
	}
}

int main() {

	node* tree = (node*)malloc(sizeof(node));
	tree = NULL;
	char c = '+';
	add(&tree, 14, &c,sizeof(char));
	c = 'i';
	add(&tree, 15, &c, sizeof(char));
	c = '*';
	add(&tree, 4, &c, sizeof(char));
	c = 'd';
	add(&tree, 5, &c, sizeof(char));
	c = '+';
	add(&tree, 2, &c, sizeof(char));
	c = 'a';
	add(&tree, 1, &c, sizeof(char));
	c = 'b';
	add(&tree, 3, &c, sizeof(char));

	/*printf("%c", search(tree, 2)->value);
	printf("%c", search(tree, 10)->value);*/

	log(tree, 0);

	delete(tree, 5);

	log(tree, 0);

	delete(tree,2);

	log(tree, 0);

}