// #pragma GCC optimize(1)
// #pragma GCC optimize(2)
// #pragma GCC optimize(3)
// #pragma GCC target("avx")
// #pragma GCC optimize("Ofast")
// #pragma GCC optimize("inline")
// #pragma GCC optimize("-fgcse")
// #pragma GCC optimize("-fgcse-lm")
// #pragma GCC optimize("-fipa-sra")
// #pragma GCC optimize("-ftree-pre")
// #pragma GCC optimize("-ftree-vrp")
// #pragma GCC optimize("-fpeephole2")
// #pragma GCC optimize("-ffast-math")
// #pragma GCC optimize("-fsched-spec")
// #pragma GCC optimize("unroll-loops")
// #pragma GCC optimize("-falign-jumps")
// #pragma GCC optimize("-falign-loops")
// #pragma GCC optimize("-falign-labels")
// #pragma GCC optimize("-fdevirtualize")
// #pragma GCC optimize("-fcaller-saves")
// #pragma GCC optimize("-fcrossjumping")
// #pragma GCC optimize("-fthread-jumps")
// #pragma GCC optimize("-funroll-loops")
// #pragma GCC optimize("-fwhole-program")
// #pragma GCC optimize("-freorder-blocks")
// #pragma GCC optimize("-fschedule-insns")
// #pragma GCC optimize("inline-functions")
// #pragma GCC optimize("-ftree-tail-merge")
// #pragma GCC optimize("-fschedule-insns2")
// #pragma GCC optimize("-fstrict-aliasing")
// #pragma GCC optimize("-fstrict-overflow")
// #pragma GCC optimize("-falign-functions")
// #pragma GCC optimize("-fcse-skip-blocks")
// #pragma GCC optimize("-fcse-follow-jumps")
// #pragma GCC optimize("-fsched-interblock")
// #pragma GCC optimize("-fpartial-inlining")
// #pragma GCC optimize("no-stack-protector")
// #pragma GCC optimize("-freorder-functions")
// #pragma GCC optimize("-findirect-inlining")
// #pragma GCC optimize("-fhoist-adjacent-loads")
// #pragma GCC optimize("-frerun-cse-after-loop")
// #pragma GCC optimize("inline-small-functions")
// #pragma GCC optimize("-finline-small-functions")
// #pragma GCC optimize("-ftree-switch-conversion")
// #pragma GCC optimize("-foptimize-sibling-calls")
// #pragma GCC optimize("-fexpensive-optimizations")
// #pragma GCC optimize("-funsafe-loop-optimizations")
// #pragma GCC optimize("inline-functions-called-once")
// #pragma GCC optimize("-fdelete-null-pointer-checks")


#define _CRT_SECURE_NO_WARNINGS
#pragma GCC optimize(3)
#pragma GCC optimize("Ofast")
#pragma GCC target("sse,sse2,sse3,ssse3,sse4,popcnt,abm,mmx,avx,tune=native")
#pragma comment(linker, "/stack:200000000")

// 玄学优化，没用的，别想了
#define _USE_MATH_DEFINES
#include <bits/stdc++.h>
using namespace std;
typedef long long LL;
// typedef __int128 LL;
typedef unsigned long long ULL;
#define SZ(x) ((int)((x).size()))

template <typename T1, typename T2>
string print_iterable(T1 begin_iter, T2 end_iter, int counter)
{
    bool done_something = false;
    stringstream res;
    res << "[";
    for (; begin_iter != end_iter and counter; ++begin_iter)
    {
        done_something = true;
        counter--;
        res << *begin_iter << ", ";
    }
    string str = res.str();
    if (done_something)
    {
        str.pop_back();
        str.pop_back();
    }
    str += "]";
    return str;
}

vector<int> SortIndex(int size, std::function<bool(int, int)> compare)
{
    vector<int> ord(size);
    for (int i = 0; i < size; i++)
        ord[i] = i;
    sort(ord.begin(), ord.end(), compare);
    return ord;
}

template <typename T>
bool MinPlace(T &a, const T &b)
{
    if (a > b)
    {
        a = b;
        return true;
    }
    return false;
}

template <typename T>
bool MaxPlace(T &a, const T &b)
{
    if (a < b)
    {
        a = b;
        return true;
    }
    return false;
}

template <typename S, typename T>
ostream &operator<<(ostream &out, const pair<S, T> &p)
{
    out << "{" << p.first << ", " << p.second << "}";
    return out;
}

template <typename T>
ostream &operator<<(ostream &out, const vector<T> &v)
{
    out << "[";
    for (int i = 0; i < (int)v.size(); i++)
    {
        out << v[i];
        if (i != (int)v.size() - 1)
            out << ", ";
    }
    out << "]";
    return out;
}

template <class TH>
void _dbg(const char *name, TH val)
{
    clog << name << ": " << val << endl;
}
template <class TH, class... TA>
void _dbg(const char *names, TH curr_val, TA... vals)
{
    while (*names != ',')
        clog << *names++;
    clog << ": " << curr_val << ", ";
    _dbg(names + 1, vals...);
}

#if DEBUG && !ONLINE_JUDGE
ifstream input_from_file("input.txt");
#define cin input_from_file

#define dbg(...) _dbg(#__VA_ARGS__, __VA_ARGS__)
#define dbg_arr(x, len) clog << #x << ": " << print_iterable(x, x + len, -1) << endl;
#else
#define dbg(...)
#define dbg_arr(x, len)
#endif

///////////////////////////////////////////////////////////////////////////
//////////////////// DO NOT TOUCH BEFORE THIS LINE ////////////////////////
///////////////////////////////////////////////////////////////////////////
#define pi acos(-1)
#define M 200010
#define endl '\n'
#define mem(a, b) memset(a, b, sizeof(a))
#define F(literate_val, max, step) for (literate_val = 0; literate_val != max; literate_val += step)
#define DMIN(a, b) (a < b ? a : b)
#define DMAX(a, b) (a > b ? a : b)
#define DMID(l, r) ((r + l - 1) >> 1)
#define INF 0x3f3f3f3f

const LL mo = 19260817;

// char buf[1<<23],*p1=buf,*p2=buf,obuf[1<<23],*O=obuf; // 或者用fread更难调的快读
// #define getchar() (p1==p2&&(p2=(p1=buf)+fread(buf,1,1<<21,stdin),p1==p2)?EOF:*p1++)

template <class T>
void print(T x)
{
    if (x < 0)
    {
        x = -x;
        putchar('-');
        // *O++ = '-';
    }
    if (x > 9)
        print(x / 10);
    putchar(x % 10 + '0');
    // *O++ = x%10 + '0'
}
// fwrite(obuf,O-obuf,1,stdout);

template <class T>
inline void qr(T &n)
{
    n = 0;
    register char c = getchar();
    LL sgn = 1;

    while (c > '9' || c < '0')
    {
        if (c == '-')
            sgn = -1;
        c = getchar();
    }

    while (c <= '9' && c >= '0')
    {
        n = (n << 3) + (n << 1) + (c ^ 0x30);
        c = getchar();
    }

    n *= sgn;
}

inline char qrc()
{
    register char c = getchar();
    while (c < 'a' || c > 'z')
        c = getchar();
    return c;
}

inline void exgcd(LL a, LL b, LL &x, LL &y)
{
    if (!b)
    {
        x = 1;
        y = 0;
        return;
    }
    exgcd(b, a % b, y, x);
    y -= a / b * x;
}

inline LL inv(LL a)
{
    LL x, y;
    exgcd(a, mo, x, y);
    return x >= 0 ? x : x + mo;
}

inline LL inv(LL a, LL mo)
{
    LL x, y;
    exgcd(a, mo, x, y);
    return x >= 0 ? x : x + mo;
}

inline void MADD(LL &x, LL y) { x = x + y < mo ? x + y : x + y - mo; }
inline void MSUB(LL &x, LL y) { x = x - y >= 0 ? x - y : x - y + mo; }

inline LL power(LL a, LL n, LL mo)
{
    LL res = 1;
    while (n)
    {
        if (n & 1)
            res = 1LL * res * a % mo;
        a = 1LL * a * a % mo;
        n >>= 1;
    }
    return res;
}

inline LL power(LL a, LL n)
{
    LL res = 1;
    while (n)
    {
        if (n & 1)
            res = 1LL * res * a;
        a = 1LL * a * a;
        n >>= 1;
    }
    return res;
}

template <class T>
T gcd(T a, T b) { return !b ? a : gcd(b, a % b); }

inline LL CRT(LL factors[], LL remains[], LL length, LL prefix_mul)
{
    LL ans = 0;
    for (auto i = 0; i < length; i++)
    {
        LL tM = prefix_mul / factors[i];
        ans += remains[i] * (tM)*inv(tM, factors[i]);
        ans %= prefix_mul;
    }
    return ans;
}

inline LL EXCRT(LL factors[], LL remains[], LL length) // 传入除数表，剩余表和两表的长度，若没有解，返回-1，否则返回合适的最小解
{
    bool valid = true;
    for (auto i = 1; i < length; i++)
    {
        LL GCD = gcd(factors[i], factors[i - 1]);
        LL M1 = factors[i];
        LL M2 = factors[i - 1];
        LL C1 = remains[i];
        LL C2 = remains[i - 1];
        LL LCM = M1 * M2 / GCD;
        if ((C1 - C2) % GCD != 0)
        {
            valid = false;
            break;
        }
        factors[i] = LCM;
        remains[i] = (inv(M2 / GCD, M1 / GCD) * (C1 - C2) / GCD) % (M1 / GCD) * M2 + C2; // 对应合并公式
        remains[i] = (remains[i] % factors[i] + factors[i]) % factors[i];                // 转正
    }
    return valid ? remains[length - 1] : -1;
}

// #define LUCASM 114514
#ifdef LUCASM
LL fact[LUCASM];

inline void get_fact(LL fact[], LL length) // 预处理阶乘用的
{
    fact[0] = 1;
    fact[1] = 1;
    for (auto i = 2; i < length; i++)
        fact[i] = fact[i - 1] * i % mo;
}

inline void get_fact(LL fact[], LL length, LL mo) // 预处理阶乘用的（求模
{
    fact[0] = 1;
    fact[1] = 1;
    for (auto i = 2; i < length; i++)
        fact[i] = fact[i - 1] * i % mo;
}

// 需要先预处理出fact[]，即阶乘
inline LL C(LL m, LL n, LL p)
{
    return m < n ? 0 : fact[m] * inv(fact[n], p) % p * inv(fact[m - n], p) % p;
}
inline LL lucas(LL m, LL n, LL p) // 求解大数组合数C(m,n) % p,传入依次是下面那个m和上面那个n和模数p（得是质数
{
    return n == 0 ? 1 % p : lucas(m / p, n / p, p) * C(m % p, n % p, p) % p;
}

#endif

// #define FrontStar 666
#ifdef FrontStar

LL edge_cnt = 0;
struct EdgeModel
{
    LL next, to, val;
} eds[FrontStar];
LL node_ctr, merged_edge_ctr, edge_ctr;
LL head[FrontStar] = {-1};

inline void add(LL u, LL v)
{
    eds[edge_cnt].to = v;
    eds[edge_cnt].next = head[u];
    head[u] = edge_cnt++;
}

inline void add(LL u, LL v, LL w)
{
    eds[edge_cnt].to = v;
    eds[edge_cnt].val = w;
    eds[edge_cnt].next = head[u];
    head[u] = edge_cnt++;
}

#endif

// #define ORAFM 2333
#ifdef ORAFM
LL prime[ORAFM + 5], prime_number = 0, prv[ORAFM + 5];
bool marked[ORAFM + 5];

void ORAfliter(LL MX, bool marked[], LL prime[], LL prv[])
{
    for (LL i = 2; i <= MX; i++)
    {
        if (!marked[i])
        {
            prime[++prime_number] = i;
            prv[i] = i;
        }
        for (LL j = 1; j <= prime_number && i * prime[j] <= MX; j++)
        {
            marked[i * prime[j]] = true;
            prv[i * prime[j]] = prime[j];
            if (i % prime[j] == 0)
                break;
        }
    }
}
#endif

#ifdef KMPM
inline void Getnext(LL next[], char t[])
{
    LL p1 = 0;
    LL p2 = next[0] = -1;
    LL strlen_t = strlen(t);
    while (p1 < strlen_t)
    {
        if (p2 == -1 || t[p1] == t[p2])
            next[++p1] = ++p2;
        else
            p2 = next[p2];
    }
}

inline void KMP(char string[], char pattern[], LL next[])
{
    LL p1 = 0;
    LL p2 = 0;
    LL strlen_string = strlen(string);
    LL strlen_pattern = strlen(pattern);
    while (p1 < strlen_string)
    {
        if (p2 == -1 || string[p1] == pattern[p2])
            p1++, p2++;
        else
            p2 = next[p2];
        if (p2 == strlen_pattern)
            printf("%lld\n", p1 - strlen_pattern + 1), p2 = next[p2];
    }
}
#endif

// #define EXKMPM 25
#ifdef EXKMPM

string pattern;
string s;
LL nxt[EXKMPM];
LL extend[EXKMPM];

void getNEXT(string &pattern, LL next[])
{
    LL pLen = pattern.length();
    LL a = 0, k = 0;

    next[0] = pLen;
    for (auto i = 1; i < pLen; i++)
    {
        if (i >= k || i + next[i - a] >= k)
        {
            if (i >= k)
                k = i;
            while (k < pLen && pattern[k] == pattern[k - i])
                k++;
            next[i] = k - i;
            a = i;
        }
        else
        {
            next[i] = next[i - a];
        }
    }
}

void EXKMP(string &s, string &pattern, LL extend[], LL next[]) // string类得配O2不然过不了
{
    LL pLen = pattern.length();
    LL sLen = s.length();
    LL a = 0, k = 0;

    getNEXT(pattern, next);

    for (auto i = 0; i < sLen; i++)
    {
        if (i >= k || i + next[i - a] >= k)
        {
            if (i >= k)
                k = i;
            while (k < sLen && k - i < pLen && s[k] == pattern[k - i])
                k++;
            extend[i] = k - i;
            a = i;
        }
        else
        {
            extend[i] = next[i - a];
        }
    }
}

#endif

#define TreeArrayM 2000010

#ifdef TreeArrayM

#define lowbit(x) (x & -x)

struct TreeArray
{
    LL *content;
    LL len;
    TreeArray(LL len)
    {
        this->content = new LL[len];
        memset(this->content, 0, sizeof(LL) * len);
        this->len = len;
    }
    LL getsum(LL pos)
    {
        LL ans = 0;
        while (pos > 0)
        {
            ans += this->content[pos];
            pos -= lowbit(pos);
        }
        return ans;
    }
    void update(LL pos, LL x)
    {
        while (pos < this->len)
        {
            this->content[pos] += x;
            pos += lowbit(pos);
        }
    }
};
#endif

// #define Aho_CorasickAutomaton 2000010

#ifdef Aho_CorasickAutomaton

#define CharacterCount 26

struct TrieNode
{
    TrieNode *son[CharacterCount], *fail;
    // LL word_count;
    LL logs;

} T[Aho_CorasickAutomaton];
vector<TrieNode *> FailEdge[Aho_CorasickAutomaton];
LL AC_counter = 0;

vector<TrieNode *> trieIndex;

TrieNode *insertWords(string &s)
{
    auto root = &T[0];
    for (auto i : s)
    {
        auto nxt = i - 'a';
        if (root->son[nxt] == NULL)
            root->son[nxt] = &T[++AC_counter];
        root = root->son[nxt];
    }
    // word_count[root]++;

    return root; // 返回含单词的节点号
} // 用例：trieIndex.push_back(insertWords(s));

TrieNode *insertWords(char *s, LL &sLen)
{
    auto root = &T[0];
    for (auto i = 0; i < sLen; i++)
    {
        auto nxt = s[i] - 'a';
        if (root->son[nxt] == NULL)
            root->son[nxt] = &T[++AC_counter];
        root = root->son[nxt];
    }
    // word_count[root]++;

    return root; // 返回含单词的节点号
}

void getFail()
{
    queue<TrieNode *> Q; // bfs用
    for (auto i = 0; i < CharacterCount; i++)
    {
        if (T[0].son[i] != NULL)
        {
            T[0].son[i]->fail = &T[0];
            Q.push(T[0].son[i]);
        }
    }
    while (!Q.empty())
    {
        auto now = Q.front();
        Q.pop();
        now->fail = now->fail == NULL ? &T[0] : now->fail;
        for (auto i = 0; i < CharacterCount; i++)
        {
            if (now->son[i] != NULL)
            {
                now->son[i]->fail = now->fail->son[i];
                Q.push(now->son[i]);
            }
            else
            {
                now->son[i] = now->fail->son[i];
            }
        }
    }
} // 先设T[0].fail=0;所有单词插完以后调用一次

LL query(string &s)
{
    auto now = &T[0];
    auto ans = 0;
    for (auto i : s)
    {
        now = now->son[i - 'a'];
        now = now == NULL ? &T[0] : now;
        now->logs++;
        // for (auto j = now; j /*&& ~word_count[j]*/; j = fail[j])
        // {
        //     // ans += word_count[j];
        //     // cout << "j:" << j << endl;
        //     // if (word_count[j])
        //     logs[j]++;
        //     // for (auto k : word_position[j])
        //     //     pattern_count[k]++;
        //     // word_count[j] = -1; // 标记已经遍历的节点
        // }
    }

    for (auto i = 1; i <= AC_counter; i++)
    {
        FailEdge[T[i].fail - T].push_back(&T[i]);
    }

    return ans;
} // 查询母串，getFail后使用一次

LL query(char *s, LL &sLen)
{
    auto now = &T[0];
    auto ans = 0;
    for (auto i = 0; i < sLen; i++)
    {
        now = now->son[s[i] - 'a'];
        now = now == NULL ? &T[0] : now;
        now->logs++;
        // for (auto j = now; j /*&& ~word_count[j]*/; j = fail[j])
        // {
        // ans += word_count[j];
        // cout << "j:" << j << endl;
        // if (word_count[j])

        // for (auto k : word_position[j])
        //     pattern_count[k]++;
        // word_count[j] = -1; // 标记已经遍历的节点
        // }
    }

    for (auto i = 1; i <= AC_counter; i++)
    {
        FailEdge[T[i].fail - T].push_back(&T[i]);
    }

    return ans;
}

void AC_dfs(TrieNode *u)
{

    for (auto i : FailEdge[u - T])
    {
        AC_dfs(i);
        u->logs += i->logs;
    }
} // query完后使用，一般搜0号点

// 输出答案使用for(auto i:trieIndex)cout<<i.logs<<endl;这样

#endif

struct Complex
{
    double re, im;
    Complex(double _re, double _im)
    {
        re = _re;
        im = _im;
    }
    Complex(double _re)
    {
        re = _re;
        im = 0;
    }
    Complex() {}
};
Complex operator+(const Complex &c1, const Complex &c2) { return Complex(c1.re + c2.re, c1.im + c2.im); }
Complex operator-(const Complex &c1, const Complex &c2) { return Complex(c1.re - c2.re, c1.im - c2.im); }
Complex operator*(const Complex &c1, const Complex &c2) { return Complex(c1.re * c2.re - c1.im * c2.im, c1.re * c2.im + c1.im * c2.re); }

void FFT(complex<double> a[], LL n, LL inv, LL rev[]) // FFT系列没有外部依赖数组，不用打ifdef
{
    for (auto i = 0; i < n; i++)
        if (i < rev[i])
            swap(a[i], a[rev[i]]);
    for (auto mid = 1; mid < n; mid <<= 1)
    {
        complex<double> tmp(cos(pi / mid), inv * sin(pi / mid));
        for (auto i = 0; i < n; i += mid << 1)
        {
            complex<double> omega(1, 0);
            for (auto j = 0; j < mid; j++, omega *= tmp)
            {
                auto x = a[i + j], y = omega * a[i + j + mid];
                a[i + j] = x + y, a[i + j + mid] = x - y;
            }
        }
    }
}

LL *FFTArrayMul(LL A[], LL B[], LL Alen, LL Blen)
{
    LL max_length = max(Alen, Blen);
    LL limit = 1;
    LL bit = 0;
    while (limit < max_length << 1)
        bit++, limit <<= 1;

    auto rev = new LL[limit + 5];
    mem(rev, 0);

    for (auto i = 0; i <= limit; i++)
        rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (bit - 1));

    // auto a = new complex<double>[limit + 5];
    // auto b = new complex<double>[limit + 5];
    complex<double> a[limit + 5], b[limit + 5];

    // for (auto i = 0; i < limit; i++)
    // {
    //     a[i] = i >= Alen ? 0 : A[Alen - i - 1] ^ 0x30;
    //     b[i] = i >= Blen ? 0 : B[Blen - i - 1] ^ 0x30;
    // } // 右对齐的输入方式，类似下面的大数乘法板子，答案需要去除前导零
    for (auto i = 0; i < max_length; i++) // 左对齐，段错误的坑，得用max_length
    {
        a[i] = A[i];
        b[i] = B[i];
    }
    static LL *c = new LL[limit + 5];
    mem(c, 0);

    FFT(a, limit, 1, rev);
    FFT(b, limit, 1, rev);

    for (auto i = 0; i <= limit; i++)
        a[i] *= b[i];

    FFT(a, limit, -1, rev); // 1是FFT变化，-1是逆变换
    for (auto i = 0; i <= limit; i++)
        c[i] = (LL)(a[i].real() / limit + 0.5); // +0.5即四舍五入
    return c;                                   // 左对齐多项式卷积结果有效位数为n+m-1
}

string FFTBigNumMul(string &A, string &B)
{

    auto max_length = max(A.length(), B.length());

    LL limit = 1;
    LL bit = 0;

    while (limit < max_length << 1)
        bit++, limit <<= 1;

    LL rev[limit + 5] = {0};

    for (auto i = 0; i <= limit; i++)
        rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (bit - 1));

    complex<double> a[limit + 5], b[limit + 5];

    for (auto i = 0; i < limit; i++)
    {
        a[i] = i >= A.length() ? 0 : A[A.length() - i - 1] ^ 0x30;
        b[i] = i >= B.length() ? 0 : B[B.length() - i - 1] ^ 0x30;
    }

    LL c[limit + 5] = {0};

    FFT(a, limit, 1, rev);
    FFT(b, limit, 1, rev);
    for (auto i = 0; i <= limit; i++)
        a[i] *= b[i];
    FFT(a, limit, -1, rev); // 1是FFT变化，-1是逆变换

    for (auto i = 0; i <= limit; i++)
        c[i] = (LL)(a[i].real() / limit + 0.5); // +0.5即四舍五入
    bool zerofliter = false;
    for (auto i = 0; i < limit; i++)
    {
        c[i + 1] += c[i] / 10;
        c[i] %= 10;
    }
    char output[limit + 5] = {0};
    LL outputPtr = 0;
    // mem(output, 0);
    for (auto i = limit; i >= 0; i--)
    {
        if (c[i] == 0 and zerofliter == 0) // 去前导零
            continue;
        zerofliter = true;
        output[outputPtr++] = c[i] ^ 0x30;
    }
    string res(output);
    if (!res.length())
        res = string("0");
    return res;
}

// #define XiongYaLi 1010
#ifdef XiongYaLi
LL pre_match[XiongYaLi];
LL searched[M];
bool dfs(LL x, const LL searchtime)
{
    if (searched[x] == searchtime)
        return false;
    searched[x] = searchtime;
    for (LL i = hds[x]; ~i; i = E[i].next)
    {
        LL obj = E[i].to;
        if (pre_match[obj] == -1 || dfs(pre_match[obj], searchtime))
        {
            pre_match[obj] = x;
            return true;
        }
    }
    return false;
}
LL get_max_match(LL lim)
{
    LL ans;
    for (LL i = 0 + 1; i < lim + 1; i++)
        ans += dfs(i, i);
    return ans;
}
#endif

// #define DINIC 520010

#ifdef DINIC
LL distant[M]; // 用于分层图层次表示
LL current[M]; // 当前弧优化
LL n, m, src, dst;

inline LL bfs()
{
	for (auto i = 1; i <= n; i++)
		distant[i] = INF;
	queue<LL> Q;
	Q.push(src);
	distant[src] = 0;
	current[src] = hds[src];
	while (!Q.empty())
	{
		auto x = Q.front();
		Q.pop();
		for (auto i = hds[x]; ~i; i = E[i].next)
		{
			auto v = E[i].to;
			if (E[i].val > 0 and distant[v] == INF)
			{
				Q.push(v);
				current[v] = hds[v];
				distant[v] = distant[x] + 1;
				if (v == dst)
					return 1;
			}
		}
	}
	return 0;
}

inline LL dfs(LL x, LL Sum)
{
	if (x == dst)
		return Sum;
	LL res = 0;
	for (auto i = current[x]; ~i and Sum; i = E[i].next) // 当前弧优化：改变枚举起点
	{
		current[x] = i;
		auto v = E[i].to;
		if (E[i].val > 0 and (distant[v] == distant[x] + 1))
		{
			LL remain = dfs(v, min(Sum, E[i].val)); // remain:当前最小剩余的流量
			if (remain == 0)
				distant[v] = INF; // 去掉增广完毕的点
			E[i].val -= remain;
			E[i ^ 1].val += remain;
			res += remain; // 经过该点的所有流量和
			Sum -= remain; // 该点剩余流量
		}
	}
	return res;
}

LL Dinic()
{
	LL ans = 0;
	while (bfs())
		ans += dfs(src, INF);
	return ans;
}
#endif

#ifdef HLPP_ENABLE
/* 除非卡时不然别用的预流推进桶排序优化黑魔法，用例如下
signed main()
{
	qr(HLPP::n);
	qr(HLPP::m);
	qr(HLPP::src);
	qr(HLPP::dst);
	while (HLPP::m--)
	{
		LL t1, t2, t3;
		qr(t1);
		qr(t2);
		qr(t3);
		HLPP::add(t1, t2, t3);
	}
	cout << HLPP::hlpp(HLPP::n + 1, HLPP::src, HLPP::dst) << endl;
	return 0;
}
*/
namespace HLPP
{
	const LL INF = 0x3f3f3f3f3f3f;
	const LL MXn = 1203;
	const LL maxm = 520010;

	vector<LL> gap;
	LL n, m, src, dst, now_height, src_height;

	struct NODEINFO
	{
		LL height = MXn, traffic;
		LL getIndex();
		NODEINFO(LL h = 0) : height(h) {}
		bool operator<(const NODEINFO &a) const { return height < a.height; }
	} node[MXn];

	LL NODEINFO::getIndex() { return this - node; }

	struct EDGEINFO
	{
		LL to;
		LL flow;
		LL opposite;
		EDGEINFO(LL a, LL b, LL c) : to(a), flow(b), opposite(c) {}
	};
	std::list<NODEINFO *> dlist[MXn];
	vector<std::list<NODEINFO *>::iterator> iter;
	vector<NODEINFO *> list[MXn];
	vector<EDGEINFO> edge[MXn];

	inline void add(LL u, LL v, LL w = 0)
	{
		edge[u].push_back(EDGEINFO(v, w, (LL)edge[v].size()));
		edge[v].push_back(EDGEINFO(u, 0, (LL)edge[u].size() - 1));
	}

	priority_queue<NODEINFO> PQ;
	inline bool prework_bfs(NODEINFO &src, NODEINFO &dst, LL &n)
	{
		gap.assign(n, 0);
		for (auto i = 0; i <= n; i++)
			node[i].height = n;
		dst.height = 0;
		queue<NODEINFO *> q;
		q.push(&dst);
		while (!q.empty())
		{
			NODEINFO &top = *(q.front());
			for (auto i : edge[&top - node])
			{
				if (node[i.to].height == n and edge[i.to][i.opposite].flow > 0)
				{
					gap[node[i.to].height = top.height + 1]++;
					q.push(&node[i.to]);
				}
			}
			q.pop();
		}

		return src.height == n;
	}

	inline void relabel(NODEINFO &src, NODEINFO &dst, LL &n)
	{
		prework_bfs(src, dst, n);
		for (auto i = 0; i <= n; i++)
			list[i].clear(), dlist[i].clear();

		for (auto i = 0; i <= n; i++)
		{
			NODEINFO &u = node[i];
			if (u.height < n)
			{
				iter[i] = dlist[u.height].insert(dlist[u.height].begin(), &u);
				if (u.traffic > 0)
					list[u.height].push_back(&u);
			}
		}
		now_height = src_height = src.height;
	}

	inline bool push(NODEINFO &u, EDGEINFO &dst) // 从x到y尽可能推流，p是边的编号
	{
		NODEINFO &v = node[dst.to];
		LL w = min(u.traffic, dst.flow);
		dst.flow -= w;
		edge[dst.to][dst.opposite].flow += w;
		u.traffic -= w;
		v.traffic += w;
		if (v.traffic > 0 and v.traffic <= w)
			list[v.height].push_back(&v);
		return u.traffic;
	}

	inline void push(LL n, LL ui)
	{
		auto new_height = n;
		NODEINFO &u = node[ui];
		for (auto &i : edge[ui])
		{
			if (i.flow)
			{
				if (u.height == node[i.to].height + 1)
				{
					if (!push(u, i))
						return;
				}
				else
					new_height = min(new_height, node[i.to].height + 1); // 抬到正好流入下一个点
			}
		}
		auto height = u.height;
		if (gap[height] == 1)
		{
			for (auto i = height; i <= src_height; i++)
			{
				for (auto it : dlist[i])
				{
					gap[(*it).height]--;
					(*it).height = n;
				}
				dlist[i].clear();
			}
			src_height = height - 1;
		}
		else
		{
			gap[height]--;
			iter[ui] = dlist[height].erase(iter[ui]);
			u.height = new_height;
			if (new_height == n)
				return;
			gap[new_height]++;
			iter[ui] = dlist[new_height].insert(dlist[new_height].begin(), &u);
			src_height = max(src_height, now_height = new_height);
			list[new_height].push_back(&u);
		}
	}

	inline LL hlpp(LL n, LL s, LL t)
	{
		if (s == t)
			return 0;
		now_height = src_height = 0;
		NODEINFO &src = node[s];
		NODEINFO &dst = node[t];
		iter.resize(n);
		for (auto i = 0; i < n; i++)
			if (i != s)
				iter[i] = dlist[node[i].height].insert(dlist[node[i].height].begin(), &node[i]);
		gap.assign(n, 0);
		gap[0] = n - 1;
		src.traffic = INF;
		dst.traffic = -INF; // 上负是为了防止来自汇点的推流
		for (auto &i : edge[s])
			push(src, i);
		src.traffic = 0;
		relabel(src, dst, n);
		for (LL ui; now_height >= 0;)
		{
			if (list[now_height].empty())
			{
				now_height--;
				continue;
			}
			NODEINFO &u = *(list[now_height].back());
			list[now_height].pop_back();
			push(n, &u - node);
		}
		return dst.traffic+INF;
	}
} 

#endif